package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

public abstract class GEShape implements Serializable{
	protected Point startP;
	protected Shape myShape;
	protected String shapeType;
	
	public GEShape(Shape shape){
		this.myShape = shape;
	}
	public void draw(Graphics2D g2D){
		g2D.draw(myShape);
	}
	abstract public void initDraw(Point shartP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();
	public Shape getMyShape() {
		return myShape;
	}
}
