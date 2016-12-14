package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class GEPolygon extends GEShape {
	private Polygon shape;
	public GEPolygon(){
		super(new Polygon());
		this.shape = (Polygon)this.getShape();

	}
	public void setDiagonal(Point p) {
		((Polygon)shape).addPoint(p.x, p.y);
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		// 이녀석들은 Rectangle이 가지고 있는 변수 갖다 쓰기만 하자
	}
	public void setX2(int x) {
		((Polygon)shape).xpoints[((Polygon)shape).npoints-1] = x;
	}
	public void setY2(int y) {
		((Polygon)shape).ypoints[((Polygon)shape).npoints-1] = y;
	}
	public int getShapeType(){
		return 4;
	}
}
