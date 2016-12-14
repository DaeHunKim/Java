package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class GEPolygon extends GEShape{
	private static final long serialVersionUID = 1L;
	private double x0,y0;
	
	public GEPolygon() {
		super(new Polygon());
	}
	public void initDrawing(Graphics g, int x, int y) {
		((Polygon) this.shape).addPoint(x, y);
		((Polygon) this.shape).addPoint(x, y);
		this.draw(g); // 한점이기 때문에 굳이 필요는 없지만 개념상 draw하자
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g); 
		((Polygon)shape).xpoints[((Polygon)shape).npoints-1] = x;
		((Polygon)shape).ypoints[((Polygon)shape).npoints-1] = y;
		this.draw(g); 
	}
	public void continueDrawing(Graphics g, int x, int y) {
		((Polygon) this.shape).addPoint(x, y);
	}
	public void finishDrawing(Graphics g, int x, int y) {
	}
	public void initMoving(Graphics g, int x, int y) {
		this.x0 = x;
		this.y0 = y;
	}
	public void keepMoving(Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D)g;
		this.draw(g2D);
		affineTransform.setToTranslation(x-x0, y-y0);
		shape = affineTransform.createTransformedShape(shape);
		this.draw(g2D);
		this.x0 = x;
		this.y0 = y;
	}
	public void finishMoving(Graphics g, int x, int y) {
		
	}
}
