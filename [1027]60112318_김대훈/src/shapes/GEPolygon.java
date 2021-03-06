package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class GEPolygon extends GEShape{
	private Polygon polygon;
	
	public GEPolygon() {
		super(new Polygon());
	}
	public void initDrawing(Graphics g, int x, int y) {
		this.polygon = (Polygon)shape;
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
		this.draw(g); // 한점이기 때문에 굳이 필요는 없지만 개념상 draw하자
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g); 
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
		this.draw(g); 
	}
	public void continueDrawing(Graphics g, int x, int y) {
		this.polygon.addPoint(x, y);
	}
	public void finishDrawing(Graphics g, int x, int y) {
//		this.draw(g);
	}
	//요거만 채우면 바로 폴리곤이 되는거야
}
