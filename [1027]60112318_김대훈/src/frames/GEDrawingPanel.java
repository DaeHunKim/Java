package frames;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EState;
import shapes.GEPolygon;
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	// attribute
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseListener;
	private Vector<GEShape> shapes; 
	// working variables
	private GEShape currentShape; 
	
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
		try {
			this.currentShape = this.currentShape.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.currentShape.initDrawing(this.getGraphics(), p.x, p.y); 
	}
	private void keepDrawing(int x, int y){
		this.currentShape.keepDrawing(this.getGraphics(), x, y); 
	}
	private void finishDraw(Point p){
		this.shapes.add(currentShape);
		this.currentShape.finishDrawing(this.getGraphics(), p.x, p.y);
	}
	private void continueDrawing(Point p) {
		currentShape.continueDrawing(this.getGraphics(), p.x, p.y);
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
			finishDraw(e.getPoint());
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
			}else if(eDrawingState == EState.NPointsDrawing){
				continueDrawing(e.getPoint());
			}
			System.out.println(eDrawingState);
		}
		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				keepDrawing(e.getX(),e.getY());
			}else if(eDrawingState == EState.NPointsDrawing){
				keepDrawing(e.getX(),e.getY());
			}
		}
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EState.TwoPointDrawing){
				eDrawingState = EState.Idle;
				finishDraw(e.getPoint());	
			}else if(eDrawingState == EState.NPointsDrawing){
				continueDrawing(e.getPoint());
			}		
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}

}
