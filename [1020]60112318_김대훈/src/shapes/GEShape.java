package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Serializable{
	private static final long serialVersionUID = 1L;
	private Shape shape; // 인터페이스이기 때문에 new는 안된다.
	protected String shapeType;

	public GEShape(Shape shape){
		this.setShape(shape);
	}
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	abstract public void draw(Graphics g);
	abstract public void setDiagonal(Point p);
	abstract public void setX2(int x);
	abstract public void setY2(int y);
	public void setShapeType(String shapeType){ this.shapeType = shapeType; }
	public int getShapeType(){return 0;}
}
