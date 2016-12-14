package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EDrawingState;
import constants.GEConstants.EState;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
//import shapes.GEEllipse;
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	// attribute
	private static final long serialVersionUID = 1L;
	
	// components
	private MouseHandler mouseListener;
	private Vector<GEShape> shapes; // 임시
	
	// working variables
	private GEShape currentShape; // 임시
	
	public void setShapeTool(GEShape shapeTool) { this.currentShape = shapeTool;}
	public GEDrawingPanel(){
		super();
		mouseListener = new MouseHandler();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		
		this.shapes = new Vector<GEShape>();
	}

	public void paint(Graphics g){
		for (GEShape shape : shapes){
			shape.draw(g);   
		}
	}
	private void initDraw(Point p) {
		switch(currentShape.getShapeType()){
			case 1 : 
				this.currentShape.setDiagonal(p); 
				this.currentShape.draw(this.getGraphics());
				break;
			case 2 :
				this.currentShape.setDiagonal(p); 
				this.currentShape.draw(this.getGraphics());
				break;
			case 3 :
				this.currentShape.setDiagonal(p); 
				this.currentShape.draw(this.getGraphics()); 
				break;
			case 4 :
				((GEPolygon)currentShape).setDiagonal(p);
				this.currentShape.draw(this.getGraphics()); 
				break;
		}
	}
	private void keepDrawing(int x, int y){
		this.currentShape.draw(this.getGraphics());
		this.currentShape.setX2(x);
		this.currentShape.setY2(y);
		this.currentShape.draw(this.getGraphics());
	}
	private void finishDraw(){
		this.shapes.add(currentShape);
	}
	private void continueDrawing(Point p) {
		((GEPolygon)currentShape).setDiagonal(p);
	}
	private class MouseHandler implements MouseInputListener{
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
			finishDraw();
			eDrawingState = EState.Idle;
		}
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EState.NPointsDrawing){
				keepDrawing(e.getX(),e.getY());
			}
		}
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EState.Idle){
				initDraw(e.getPoint());
				if(currentShape instanceof GEPolygon){
					eDrawingState = EState.NPointsDrawing;
				}else{
					eDrawingState = EState.TwoPointDrawing;
				}
			}
			System.out.println(eDrawingState);
		}
		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				keepDrawing(e.getX(),e.getY());
			}
		}
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				eDrawingState = EState.Idle;
				finishDraw();	
			}
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}

}
