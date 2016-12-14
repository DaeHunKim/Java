package shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape {
	public GEPolygon(){
		super(new Polygon());
	}
	public void initDraw(Point shartP) {
		((Polygon) myShape).addPoint(shartP.x, shartP.y);
	}
	public void setCoordinate(Point currentP) {
		((Polygon)myShape).xpoints[((Polygon)myShape).npoints-1] = currentP.x;
		((Polygon)myShape).ypoints[((Polygon)myShape).npoints-1] = currentP.y;
	}
	public void continueDrawing(Point continueP){
		((Polygon)myShape).addPoint(continueP.x, continueP.y);
	}
	public GEShape clone() {
		return new GEPolygon();
	}
}
