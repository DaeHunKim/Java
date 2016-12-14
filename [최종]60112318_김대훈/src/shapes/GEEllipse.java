package shapes;

import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	private static final long serialVersionUID = 1L;
	private Ellipse2D ellipse;
	public Ellipse2D getEllipse() {return ellipse;}
	public void setEllipse(Ellipse2D ellipse) {this.ellipse = ellipse;}
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
		this.ellipse = (Ellipse2D)this.getShape();
	}
	public GEEllipse(Ellipse2D copy){
		super(new Ellipse2D.Double(copy.getX(), copy.getY(), copy.getWidth(), copy.getHeight()));
		this.ellipse = (Ellipse2D) this.getShape();
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
		Ellipse2D tempEllipse = (Ellipse2D)this.getShape();
		tempEllipse.setFrameFromDiagonal(this.px, this.py, x, y);
	}
	@Override
	public GEShape deepCopy() {
		GEEllipse ellipse = new GEEllipse(this.getEllipse());
		ellipse.setGraphicsAttributes(this);
		return ellipse;
	}
}
