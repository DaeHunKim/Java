package shapes;

import java.awt.Graphics;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	private Line2D line;
	
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
}
