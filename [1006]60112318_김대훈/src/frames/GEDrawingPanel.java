package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EState;
import shapes.GEPolygon;
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	private MouseHandler mouseListener;
	private GEShape shapeTool; //이변수에 받아서 어떤 도형을 그릴지 확인
	private ArrayList<GEShape> shapeList;
	private EState currentState;
	
	public GEDrawingPanel(){
		super();
		currentState = EState.Idle;
		shapeList = new ArrayList<GEShape>();
		mouseListener = new MouseHandler();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
	}
	
	public void setShapeTool(GEShape shapeTool) {this.shapeTool = shapeTool;}

	public void paintComponent(Graphics g){
		super.paintComponent(g);            
		Graphics2D g2D = (Graphics2D)g;
		for(GEShape shape : shapeList){
			shape.draw(g2D);
		}
	}
	
	private void initDraw(Point startP) {
		shapeTool = shapeTool.clone();
		shapeTool.initDraw(startP);
	}
	
	private void animateDraw(Point currentP) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shapeTool.getMyShape());
		shapeTool.setCoordinate(currentP);
		g2D.draw(shapeTool.getMyShape());
	}

	private void continueDrawing(Point p){
		((GEPolygon)shapeTool).continueDrawing(p);
	}
	
	private void finishDraw(GEShape shape){
		shapeList.add(shape);
		currentState = EState.Idle;
		repaint();
	}
	
	private class MouseHandler implements MouseInputListener{
		public void mousePressed(MouseEvent e) {
			if(currentState == EState.Idle){
				initDraw(e.getPoint());
				if(shapeTool instanceof GEPolygon){
					currentState = EState.NPointsDrawing;
				}else{
					currentState = EState.TwoPointDrawing;
				}
			}
			System.out.println(currentState);
		}
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointDrawing){
				finishDraw(shapeTool);	
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(currentState != EState.Idle){
				animateDraw(e.getPoint());				
			}
		}
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointsDrawing){
				animateDraw(e.getPoint());
			}
		}
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointsDrawing){
					if(e.getClickCount() == 1 ){
						continueDrawing(e.getPoint());
					}else if(e.getClickCount() == 2){
						finishDraw(shapeTool);
						repaint();
					}
				}
			}
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}
}
