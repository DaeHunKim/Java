package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.TextComponent;
import java.awt.TextArea;
import java.awt.TextField;

public class GEText extends GEShape{
	public GEText(){
		super(new Line2D.Double());
	}
	public void setCoordinate(Point currentP) {
	}
	public void initDraw(Point shartP) {
	}
	public GEShape clone() {
		return null;
	}
}
