package shapes;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	private Ellipse2D ellipse;
	
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
	public void finishDrawing(Graphics g, int x, int y) {	}
}
