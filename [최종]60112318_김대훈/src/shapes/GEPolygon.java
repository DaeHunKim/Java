package shapes;

import java.awt.Polygon;

public class GEPolygon extends GEShape{
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	public Polygon getPolygon() {return polygon;}
	public void setPolygon(Polygon polygon) {this.polygon = polygon;}
	
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	public GEPolygon(Polygon copy){
		super(new Polygon(copy.xpoints, copy.ypoints, copy.npoints));
		this.polygon = (Polygon) this.getShape();
	}
	@Override
	public void setPoint(int x, int y) {
		((Polygon)this.getShape()).addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		((Polygon)this.getShape()).addPoint(x, y);	
	}
	@Override
	public void movePoint(int x, int y) {
		Polygon tempPolygon = (Polygon)this.getShape();
		tempPolygon.xpoints[((Polygon)this.getShape()).npoints-1] = x;
		tempPolygon.ypoints[((Polygon)this.getShape()).npoints-1] = y;
		if(this.anchors!=null){
			this.anchors.setAnchorGeo(this.getShape().getBounds().x, this.getShape().getBounds().y,
					this.getShape().getBounds().width, this.getShape().getBounds().height);
		}
	}
	@Override
	public GEShape deepCopy() {
		GEPolygon polygon = new GEPolygon(this.getPolygon());
		polygon.setGraphicsAttributes(this);
		return polygon;
	}
}
