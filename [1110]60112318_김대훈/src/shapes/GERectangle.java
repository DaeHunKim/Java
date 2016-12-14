package shapes;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GERectangle extends GEShape {
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	private int x0, y0;
	
	public GERectangle() {
		super(new Rectangle());
	}
	public void initDrawing(Graphics g, int x, int y) {
		rectangle = (Rectangle)shape;
		rectangle.setFrame(x, y, 0, 0);
		this.draw(g);
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
		this.draw(g);
	}
	public void continueDrawing(Graphics g, int x, int y) {	}
	public void finishDrawing(Graphics g, int x, int y) {	}
	public void initMoving(Graphics g, int x, int y) {
		this.x0 = x;
		this.y0 = y;
	}
	public void keepMoving(Graphics g, int x, int y) {
		this.draw(g);
		rectangle.setLocation(rectangle.x+x-x0, rectangle.y+y-y0);
		this.draw(g);
		this.x0 = x;
		this.y0 = y;
	}
	public void finishMoving(Graphics g, int x, int y) {	}
}

