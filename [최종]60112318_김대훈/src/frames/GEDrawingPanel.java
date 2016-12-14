package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GEConstants;
import constants.GEConstants.EState;
import entity.GEModel;
import entity.GEStack;
import shapes.GEGroup;
import shapes.GEPolygon;
import shapes.GEShape;
import shapes.GEShape.EAnchors;
import transformer.GEDrawer;
import transformer.GEGrouper;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotator;
import transformer.GETransformer;

public class GEDrawingPanel extends JPanel{
	// attributes 
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;	
	public boolean isUpdated(){return this.bUpdated;}

	// components  
	private MouseHandler mouseHandler;
	
	private Vector<GEShape> shapes;
	public Vector<GEShape> getShapes() {return this.shapes;}
	
	// association variables 
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	// working variables 
	private GEShape selectedShape;
	private void setSelectedShape(GEShape selectedShape){
		if(this.selectedShape != null){
			this.selectedShape.setSelected(false);
		}
		this.selectedShape = selectedShape;
		if(this.selectedShape != null){
			this.selectedShape.setSelected(true);
		}
	}
	private GEShape getSelectedShape(){return this.selectedShape;}
	
	private GETransformer currentTransformer; // 되도록이면 기능을 최소화하기 위해 분리하는 것
	private Color lineColor, fillColor;
	
	private GEStack stack;

	public GEDrawingPanel() {
		// class attributes
		this.bUpdated = false;
		// create components
		this.shapes = new Vector<GEShape>();
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.stack = new GEStack();
		initializeGraphicsAttributes();
	}	
	
	public void init() {
	}
	
	public void redo(){
		if(this.stack!=null){
			this.shapes = this.stack.redoPop();
			this.repaint();
		}
	}
	
	public void undo(){
		if(this.shapes!=null){
			this.shapes = this.stack.pop();
			this.repaint();
		}
	}
	
	public void newShapes(){
		this.shapes.removeAllElements();
		this.repaint();
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>)GEModel.read(fileName);			
			this.bUpdated = false;
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
//			e.printStackTrace();
		}
	}

	public void saveShapes(String fileName) {
		try {
		   	if(!fileName.endsWith(GEConstants.SFILEEXTENSION)){
	    		GEModel.save( ".\\" + GEConstants.SFILECONFIG, fileName);
		   	}else{
				GEModel.save(fileName, this.shapes);
				this.bUpdated = false;
		   	}
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	public void paste(Vector<GEShape> shapeList) {
		for(GEShape shape : shapeList){
			this.shapes.add(shape);
		}
		repaint();
	}
	
	public Vector<GEShape> copy() {
		Vector<GEShape> returnList = new Vector<GEShape>();
		for(GEShape shape : this.shapes){
			if(shape.isSelected()){
				returnList.add(shape.deepCopy());
			}
		}
		return returnList;
	}
	
	public Vector<GEShape> cut() {
		Vector<GEShape> returnList = new Vector<GEShape>();
		for(int i = this.shapes.size(); i > 0 ; i--){
			GEShape shape = this.shapes.get(i-1);
			if(shape.isSelected()){
				returnList.add(0, shape);
				this.shapes.remove(shape);
			}
		}
		repaint();
		return returnList;
	}

	public void delete() {
		for(int i = this.shapes.size(); i > 0 ; i--){
			GEShape shape = this.shapes.get(i-1);
			if(shape.isSelected()){
				this.shapes.remove(shape);
			}
		}
		repaint();
	}
	
	public void group() {
		if(this.selectedShape!=null && this.selectedShape.isSelected()){
			// GEGroup 객체 생성
			GEGroup group = new GEGroup();
			if(this.getSelectedShape().getClass().getSimpleName().equals("GEGroup")){
				group = (GEGroup)this.getSelectedShape();				
			}
			// Shape들을 담는 tempList 생성
			Vector<GEShape> tempList = new Vector<GEShape>();
			// tempList에 기존의 저장된 List를 복사(Shallow copy)
			tempList.addAll(shapes);
			// 현재 저장된 shape만큼 돌면서
			for (GEShape shape : tempList) {
				// group에 해당 쉐이프가 들어있으면 그룹에 포함하고
				if (group.addShape(shape)) {
					// 해당 도형 선택 해제
					shape.setSelected(false);
					// 연결 리스트에서 해당 도형 제거
					shapes.remove(shape);
				}
			}
			// 그룹화한 도형을 선택
			group.setSelected(true);
			// 그룹 전체를 연결리스트에 저장
			shapes.add(group);
			repaint();		
		}
	}
	
	public void unGroup() {
		Vector<GEShape> tempList = new Vector<GEShape>();
		for(int i = shapes.size() ; i > 0 ; i--){
			GEShape shape = shapes.get(i-1);
			if(shape instanceof GEGroup && shape.isSelected()){
				for(GEShape childShape : ((GEGroup)shape).getChildList()){
					childShape.setSelected(true);
					tempList.add(childShape);
				}
				shapes.remove(shape);
			}
		}
		shapes.addAll(tempList);
		repaint();
	}
	
	public void selectAll() {
		this.setSelectedShape(new GEGroup());
		this.currentTransformer = new GEGrouper(getSelectedShape());
		GEGroup group = (GEGroup)this.getSelectedShape();
		Vector<GEShape> tempList = new Vector<GEShape>();
		tempList.addAll(shapes);
		group.initializeBound();
		for (GEShape shape : tempList) {
			shape.setSelected(true);	
		}
		repaint();
	}

	public void initializeGraphicsAttributes(){
		this.lineColor = GEConstants.COLOR_LINE_DEFAULT;
		this.fillColor = GEConstants.COLOR_FILL_DEFAULT;
	}

	public void setLineColor(Color lineColor){
		if(selectedSetColor(true, lineColor)){
		}
		this.lineColor = lineColor;
		return;
	}
	
	public void setFillColor(Color fillColor){
		if(selectedSetColor(false, fillColor)){
		}
		this.fillColor = fillColor;
		return;
	}
	
	private boolean selectedSetColor(boolean flag, Color color) {
		if(selectedShape != null){
			if(flag){
				selectedShape.setLineColor(color);
			}else{
				selectedShape.setFillColor(color);
			}
			repaint();
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (GEShape shape: this.shapes) {
			shape.draw(g);
		}
	}
	
	private GEShape createShape(){
		try {
			this.setSelectedShape(this.currentTool.getClass().newInstance());
			this.getSelectedShape().setLineColor(this.lineColor);
			this.getSelectedShape().setFillColor(this.fillColor);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return getSelectedShape();
	}

	private void clearSelectedShape(){
		for(GEShape shape : this.shapes){
			shape.setSelected(false);
		}
		repaint();
	}	
	private void setSelected(){
		for(GEShape shape : this.shapes){
			shape.setSelected(false);
		}
		this.getSelectedShape().setSelected(true);
	}
	private void initTransforming(Point p) {
		this.currentTransformer.initTransforming(this.getGraphics(), p.x, p.y);
	}
	private void keepTransforming(Point p) {
		this.currentTransformer.keepTransforming(this.getGraphics(), p.x, p.y);
	}
	private void continueTransforming(Point p) {
		this.currentTransformer.continueTransforming(this.getGraphics(), p.x, p.y);
	}
	private void finishTransforming(Point p) {
		this.currentTransformer.finishTransforming(this.shapes);
		this.setSelected();
		this.stack.undoPush(shapes);
		this.stack.redoPush(shapes);
		this.bUpdated = true;
		GEGroup group = null;
		if (this.currentTransformer.getClass().getSimpleName().equals("GEGrouper")) {
			if (this.getSelectedShape().getClass().getSimpleName().equals("GEGroup")) {
				group = (GEGroup)this.getSelectedShape();
				Vector<GEShape> tempList = new Vector<GEShape>();
				tempList.addAll(shapes);
				for (GEShape shape : tempList) {
					// group에 해당 쉐이프가 들어있으면 그룹에 포함하고
					if(group.onShape(shape)){
						shape.setSelected(true);	
					}
				}
			}
		}
		// 선택도형이 GEGroup이 아니면 List에 저장
		if(!(this.getSelectedShape() instanceof GEGroup)){
			if (currentTransformer.inValidShape(p.x, p.y)) {
				this.shapes.add(getSelectedShape());
			}
		}
		this.repaint();
	}
	private GEShape onShape(Point p) {
		for(GEShape shape : this.shapes){
			if(shape.onShape(p))
				return shape; 
		}
		return null;
	}
	
	private void setCursor(int x, int y){
		Point p = new Point(x, y);
		if(onShape(p)==null){
			setCursor(GEConstants.DEFAULT_CURSOR);
		}else {
			switch (onShape(p).geteSelectedAnchor()){
			case EE: setCursor(GEConstants.EE_RESIZE_CURSOR); break;
			case WW: setCursor(GEConstants.WW_RESIZE_CURSOR); break;
			case SS: setCursor(GEConstants.SS_RESIZE_CURSOR); break;
			case NN: setCursor(GEConstants.NN_RESIZE_CURSOR); break;
			case NE: setCursor(GEConstants.NE_RESIZE_CURSOR); break;
			case NW: setCursor(GEConstants.NW_RESIZE_CURSOR); break;
			case SE: setCursor(GEConstants.SE_RESIZE_CURSOR); break;
			case SW: setCursor(GEConstants.SW_RESIZE_CURSOR); break;
			case RR: setCursor(GEConstants.ROTATE_CURSOR); break;
			case MM: setCursor(GEConstants.MOVE_CURSOR); break;
			}
		}
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		private EState eDrawingState = EState.Idle;
		public void mouseClicked(MouseEvent e) {
			if(eDrawingState == EState.NPointsDrawing){
				if(e.getClickCount() == 1 ){
					mouse1Clicked(e);
				}else if(e.getClickCount() == 2){
					mouse2Clicked(e);
				}
			}
		}
		private void mouse1Clicked(MouseEvent e) {
			continueTransforming(e.getPoint());
		}
		private void mouse2Clicked(MouseEvent e) {
			if(eDrawingState == EState.NPointsDrawing){
				finishTransforming(e.getPoint());
				eDrawingState = EState.Idle;
				getSelectedShape().setSelected(true);
			}
		}
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EState.Idle){
				setCursor(e.getX(), e.getY());
			}else if(eDrawingState == EState.NPointsDrawing){
				keepTransforming(e.getPoint());
			}
		}
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EState.Idle) {
				// 마우스 밑에 그림이 있는지 확인
				setSelectedShape(onShape(e.getPoint()));
				// 그림이 없으면
				if (getSelectedShape() == null) {
					// 그림 그리기 시작
					if(currentTool instanceof GEGroup){
						clearSelectedShape();
						setSelectedShape(createShape());
						currentTransformer = new GEGrouper(getSelectedShape());
						eDrawingState = EState.TwoPointDrawing;
					}else{
						clearSelectedShape();
						setSelectedShape(createShape());
						currentTransformer = new GEDrawer(getSelectedShape());
						// 도구가 polygon이면 상태를 NPointsDrawing
						if (currentTool instanceof GEPolygon) {
							eDrawingState = EState.NPointsDrawing;
							// 도구가 polygon이 아니면 상태를 TwoPointDrawing
						} else {
							eDrawingState = EState.TwoPointDrawing;
						}
					}
					initTransforming(e.getPoint());
					// 그림이 밑에 있으면
				} else {
					clearSelectedShape();
					getSelectedShape().setSelected(true);
					if (getSelectedShape().geteSelectedAnchor() == EAnchors.MM) {
						currentTransformer = new GEMover(getSelectedShape());
						eDrawingState = EState.Moving;
					} else if (getSelectedShape().geteSelectedAnchor() == EAnchors.RR) {
						currentTransformer = new GERotator(getSelectedShape());
						eDrawingState = EState.Rotate;
					} else { // resize 실행
						currentTransformer = new GEResizer(getSelectedShape());
						eDrawingState = EState.Resize;
					}
					initTransforming(e.getPoint());
				}
			} else if (eDrawingState == EState.NPointsDrawing) {
				continueTransforming(e.getPoint());
			}
			System.out.println(eDrawingState);
		}

		public void mouseDragged(MouseEvent e) {
			if(eDrawingState != EState.Idle)
				keepTransforming(e.getPoint());			
		}
	
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EState.NPointsDrawing){
				continueTransforming(e.getPoint());
			}else{
				finishTransforming(e.getPoint());	
				eDrawingState = EState.Idle;
			}
			System.out.println(eDrawingState);
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
}
