package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GERectangle extends GEShape {
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	public Rectangle getRectangle() {return rectangle;}
	public void setRectangle(Rectangle rectangle) {	this.rectangle = rectangle;}

	public GERectangle() {
		super(new Rectangle());
		this.rectangle = (Rectangle) this.getShape();
	}
	public GERectangle(Rectangle copy){
		super(new Rectangle(copy.x, copy.y, copy.width, copy.height));
		this.rectangle = (Rectangle) this.getShape();
	}
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void addPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle tempRectangle = (Rectangle)this.getShape();
		tempRectangle.setFrameFromDiagonal(this.px, this.py, x, y);
	}
	public GEShape deepCopy() {
//		GERectangle rect = new GERectangle(this.getRectangle());
//		rect.setGraphicsAttributes(this);
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(this.getShape());
		GERectangle rect = new GERectangle();
		rect.setShape(newShape);
		rect.setGraphicsAttributes(this);
		return rect;
	}
}

