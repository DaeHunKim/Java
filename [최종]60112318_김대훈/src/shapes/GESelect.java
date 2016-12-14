package shapes;

import java.awt.Rectangle;

public class GESelect extends GEShape{
	private static final long serialVersionUID = 1L;
	private int px, py;

	public GESelect(){
		super(new Rectangle());	
	}

	@Override
	public void setPoint(int x, int y) {
//		this.rectangle.setLocation(x, y);
		this.px = x;
		this.py = y;
	}

	@Override
	public void addPoint(int x, int y) {
//		this.rectangle.setSize(x-this.rectangle.x, y-rectangle.y);
		Rectangle tempRectangle = (Rectangle)this.getShape();
		tempRectangle.setFrameFromDiagonal(px, py, x, y);
	}
	@Override
	public void movePoint(int x, int y) {
	}
	@Override
	public void moveShape(int x, int y) {
	}
	@Override
	public GEShape deepCopy() {
		return null;
	}

}
