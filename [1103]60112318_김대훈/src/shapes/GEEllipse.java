package shapes;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	private static final long serialVersionUID = 1L;
	private Ellipse2D ellipse;
	private double x0,y0;
	private double width, height;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}
	public void initDrawing(Graphics g, int x, int y) {
		ellipse = (Ellipse2D)shape;
		ellipse.setFrame(x, y, 0, 0);
		this.draw(g);
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), x-ellipse.getX(), y-ellipse.getY());
		this.draw(g);
	}
	public void continueDrawing(Graphics g, int x, int y) {	}
	public void finishDrawing(Graphics g, int x, int y) {
		this.width = x-ellipse.getX();
		this.height = y-ellipse.getY();
	}
	
	public void initMoving(Graphics g, int x, int y) {
		this.x0 = x;
		this.y0 = y;
	}
	public void keepMoving(Graphics g, int x, int y) {
		this.draw(g);
		ellipse.setFrame(ellipse.getX()+x-x0, ellipse.getY()+y-y0, width, height);
		this.draw(g);
		this.x0 = x;
		this.y0 = y;
	}
	public void finishMoving(Graphics g, int x, int y) {}

}
