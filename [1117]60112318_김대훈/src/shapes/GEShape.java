package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

abstract public class GEShape implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Shape shape; 
	protected AffineTransform affineTransform;

	public GEShape(Shape shape){
		this.shape = shape;
		affineTransform = new AffineTransform();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);
	}	
	public boolean onShape(Point p){
		if(this.shape.intersects(p.x, p.y, 1, 1)){
			return true;
		}
		return false;
	}
	abstract public void initDrawing(Graphics g, int x, int y);
	abstract public void keepDrawing(Graphics g, int x, int y);
	abstract public void continueDrawing(Graphics g, int x, int y);
	abstract public void finishDrawing(Graphics g, int x, int y);
	
	abstract public void initMoving(Graphics g, int x, int y);
	abstract public void keepMoving(Graphics g, int x, int y);
	abstract public void finishMoving(Graphics g, int x, int y);
}
