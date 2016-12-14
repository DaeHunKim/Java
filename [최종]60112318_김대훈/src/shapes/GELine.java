package shapes;

import java.awt.geom.Line2D;

public class GELine extends GEShape{
	private static final long serialVersionUID = 1L;
	private Line2D line;
	public Line2D getLine() {return line;}
	public void setLine(Line2D line) {this.line = line;}
	public GELine() {
		super(new Line2D.Double());
		this.line = (Line2D)this.getShape();
	}
	public GELine(Line2D copy){
		super(new Line2D.Double(copy.getX1(), copy.getY1(), copy.getX2(), copy.getY2()));
		this.line = (Line2D) this.getShape();
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
		Line2D tempLine = (Line2D)this.getShape();
		tempLine.setLine(this.px, this.py, x, y);
	}
	@Override
	public GEShape deepCopy() {
		GELine line = new GELine(this.getLine());
		line.setGraphicsAttributes(this);
		return line;
	}
}
