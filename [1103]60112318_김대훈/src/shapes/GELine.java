package shapes;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GELine extends GEShape{
	private static final long serialVersionUID = 1L;
	private Line2D line;
	private double x0, y0;
	private Point2D startP, endP;
	
	public GELine() {
		super(new Line2D.Double());
	}
	public void initDrawing(Graphics g, int x, int y) {
		line = (Line2D)shape;
		line.setLine(x, y, x, y);
		this.draw(g);
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		line.setLine(line.getX1(), line.getY1(), x, y);
		this.draw(g);
	}
	public void continueDrawing(Graphics g, int x, int y) {	}
	public void finishDrawing(Graphics g, int x, int y) { }
	
	public void initMoving(Graphics g, int x, int y) {
		startP = line.getP1();
		endP = line.getP2();
		this.x0 = x;
		this.y0 = y;
	}
	public void keepMoving(Graphics g, int x, int y) {
		this.draw(g);
		line.setLine(line.getX1()+x-x0, line.getY1()+y-y0, line.getX2()+x-x0, line.getY2()+y-y0);
		this.draw(g);
		this.x0 = x;
		this.y0 = y;
	}
	public void finishMoving(Graphics g, int x, int y) {
		
	}
}
