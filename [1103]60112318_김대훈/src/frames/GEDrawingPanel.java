package frames;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GEConstants;
import constants.GEConstants.EState;
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes;
	// working variables
	private GEShape currentTool, selectedShape;
	public void setcurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	public Vector<GEShape> getShapeList(){ return shapes; }
	public void setShapeList(Vector<GEShape> shapes){ 
		this.shapes = shapes;	
		repaint();
	}

	public GEDrawingPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapes = new Vector<GEShape>();
	}
	
	public void paintComponent(Graphics g) {
		for (GEShape shape: shapes) {
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
		this.shapes.add(selectedShape);
	}

	private void initMoving(Point p) {
		this.selectedShape.initMoving(this.getGraphics(), p.x, p.y);
	}
	
	private void keepMoving(Point p) {
		this.selectedShape.keepMoving(this.getGraphics(), p.x, p.y);
	}
	
	private void finishMoving(Point p) {
		this.selectedShape.finishMoving(this.getGraphics(), p.x, p.y);
	}
	
	private GEShape onShape(Point p) {
		for(GEShape shape : shapes){
			if(shape.onShape(p))
				return shape; 
		}
		return null;
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
