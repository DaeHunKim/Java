package frames;

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
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	// attributes 
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated(){return this.bUpdated;}

	// components  
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes = new Vector<GEShape>();
	public Vector<GEShape> getShapes() {return this.shapes;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	
	// association variables 
	private GEShape currentTool;
	public void setcurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	// working variables 
	private GEShape selectedShape;

	public GEDrawingPanel() {
		this.bUpdated = false;
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}	

	public void init() {
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>)GEModel.read(fileName);			
			this.bUpdated = false;
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	
	public Vector<GEShape> getShapeList(){ return this.getShapes(); }
	
	public void setShapeList(Vector<GEShape> shapes){ 
		this.setShapes(shapes);	
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (GEShape shape: this.getShapes()) {
			shape.draw(g);
		}
	}
	
	private void initDrawing(Point p) {
		try {
			this.selectedShape = this.currentTool.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.selectedShape.initDrawing(this.getGraphics(), p.x, p.y);	
	}
	private void keepDrawing(Point p) {
		this.selectedShape.keepDrawing(this.getGraphics(), p.x, p.y);	
	}
	private void continueDrawing(Point p) {
		this.selectedShape.continueDrawing(this.getGraphics(), p.x, p.y);	
	}
	private void finishDrawing(Point p) {
		this.selectedShape.finishDrawing(this.getGraphics(), p.x, p.y);	
		bUpdated = true;
		this.getShapes().add(selectedShape);
	}

	private void initMoving(Point p) {
		this.selectedShape.initMoving(this.getGraphics(), p.x, p.y);
	}
	
	private void keepMoving(Point p) {
		this.selectedShape.keepMoving(this.getGraphics(), p.x, p.y);
	}
	
	private void finishMoving(Point p) {
		this.selectedShape.finishMoving(this.getGraphics(), p.x, p.y);
		bUpdated = true;
	}
	
	private GEShape onShape(Point p) {
		for(GEShape shape : this.getShapes()){
			if(shape.onShape(p))
				return shape; 
		}
		return null;
	}
	
	public void newShapes(){
		this.shapes.removeAllElements();
		this.repaint();
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
			continueDrawing(e.getPoint());
		}
		private void mouse2Clicked(MouseEvent e) {
			finishDrawing(e.getPoint());
			eDrawingState = EState.Idle;
		}
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EState.NPointsDrawing){
				keepDrawing(e.getPoint());
			}
		}
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EState.Idle){
				// 마우스 밑에 그림이 있는지 확인
				selectedShape = onShape(e.getPoint());
				// 그림이 없으면
				if(selectedShape == null){
					// 도구가 polygon이 아니면
					if(!currentTool.getClass().getSimpleName().equals("GEPolygon")){
						// 그림 그리기 시작
						initDrawing(e.getPoint());
						eDrawingState = EState.TwoPointDrawing;
					}else if(currentTool.getClass().getSimpleName().equals("GEPolygon")){
						initDrawing(e.getPoint());
						eDrawingState = EState.NPointsDrawing;
					}
				//그림이 밑에 있으면
				}else{
					// 움직이기 시작
					initMoving(e.getPoint());
					eDrawingState = EState.Moving;
					System.out.println("Move");
				}
			}else if(eDrawingState == EState.NPointsDrawing){
				continueDrawing(e.getPoint());
			}
			System.out.println(eDrawingState);
		}

		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				keepDrawing(e.getPoint());
			}else if(eDrawingState == EState.NPointsDrawing){
				keepDrawing(e.getPoint());
			}else if(eDrawingState == EState.Moving){
				keepMoving(e.getPoint());
			}
		}
	
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				eDrawingState = EState.Idle;
				finishDrawing(e.getPoint());	
			}else if(eDrawingState == EState.NPointsDrawing){
				continueDrawing(e.getPoint());
			}else if(eDrawingState == EState.Moving){
				eDrawingState = EState.Idle;
				finishMoving(e.getPoint());	
			}
			System.out.println(eDrawingState);
		}

		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}
}
