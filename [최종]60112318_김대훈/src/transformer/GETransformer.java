package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import constants.GEConstants;
import shapes.GEShape;

public abstract class GETransformer {
	// drawing, moving, resizing, rotating의 슈퍼클래스
	private GEShape shape;
	protected Point previousP, anchorP;
	protected BasicStroke dashedLineStroke; 
	protected AffineTransform affineTransform;
	public Point getAnchorP() { return anchorP; }

	public GETransformer(GEShape shape){
		this.shape = shape;
		this.previousP = new Point(0, 0);
		this.anchorP = new Point(0, 0);
		this.affineTransform = new AffineTransform();
		float dashes[] = {GEConstants.DEFAULT_DASH_OFFSET};
		this.dashedLineStroke = new BasicStroke(GEConstants.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
	}
	
	public GEShape getShape() {return shape;}
	public void setShape(GEShape shape) {this.shape = shape;}
	
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);
	abstract public void finishTransforming(Vector<GEShape> shapeList);
	abstract public boolean inValidShape(int x, int y);
}
