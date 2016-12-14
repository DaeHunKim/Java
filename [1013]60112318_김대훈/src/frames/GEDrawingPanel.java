package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EDrawingState;
import constants.GEConstants.EState;

public class GEDrawingPanel extends JPanel {
	private MouseHandler mouseListener;
	private Vector<Rectangle> rectangles; // 임시
	private Rectangle currentShape; // 임시

	public GEDrawingPanel(){
		super();
		mouseListener = new MouseHandler();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		
		this.rectangles = new Vector<Rectangle>();
	}
	
	public void paint(Graphics g){
		for (Rectangle shape : rectangles){
			shape.draw(g);   // 클래스안에 함수로 만들어놓으니까 다시 그릴때도 쉬워.. Because 함수를 호출만 하니까 굉장히 간단해졌어
		}
	}
	//프레임한테 컴포넌트를 받아서 다시 그리라고 하는 함수
	
	private void initDraw(int x, int y) {
		this.currentShape = new Rectangle(x,y,x,y);
	}
	private void keepDrawing(int x, int y){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		this.currentShape.draw(g2D);
		this.currentShape.setX2(x);
		this.currentShape.setY2(y);
		this.currentShape.draw(g2D);
	}
	private void finishDraw(){
		rectangles.add(currentShape);
	}
	private class Rectangle{		// 저장을 해볼꺼야
		private int x1, y1, x2, y2;
		public Rectangle(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public int getX1() {return x1;}
		public void setX1(int x1) {this.x1 = x1;}
		public int getY1() {return y1;}
		public void setY1(int y1) {this.y1 = y1;}
		public int getX2() {return x2;}
		public void setX2(int x2) {this.x2 = x2;}
		public int getY2() {return y2;}
		public void setY2(int y2) {this.y2 = y2;}
		
		public void draw(Graphics g){
			Graphics2D g2D = (Graphics2D)g;
			g2D.setColor(Color.MAGENTA);
			g2D.setXORMode(g2D.getBackground());
			g2D.drawRect(x1, y1, x2-x1, y2-y1);		
		}
	}
	
	private class MouseHandler implements MouseInputListener{
		private EDrawingState eDrawingState = EDrawingState.idle;
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1 ){
				mouse1Clicked(e);
			}else if(e.getClickCount() == 2){
				mouse2Clicked(e);
			}
		}
		private void mouse1Clicked(MouseEvent e) {

		}
		private void mouse2Clicked(MouseEvent e) {

		}
		public void mouseMoved(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EDrawingState.idle){
				initDraw(e.getX(),e.getY());
				eDrawingState = EDrawingState.drawing;
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EDrawingState.drawing){
				keepDrawing(e.getX(),e.getY());
			}
		}
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EDrawingState.drawing){
				eDrawingState = EDrawingState.idle;
				finishDraw();	
			}
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}
}
