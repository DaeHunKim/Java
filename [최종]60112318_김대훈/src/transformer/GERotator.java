package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import constants.GEConstants;
import shapes.GEShape;

public class GERotator extends GETransformer {
	private Point centerP;
	
	public GERotator(GEShape shape) {
		super(shape);
	}
	@Override
	public void initTransforming(Graphics g, int x, int y) {
		this.previousP.x = x;
		this.previousP.y = y;
		this.centerP = new Point((int)this.getShape().getBounds().getCenterX(), (int)this.getShape().getBounds().getCenterY());
	}
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(this.dashedLineStroke);
		
		double rotationAngle = computeRotationAngle(this.centerP, this.previousP, new Point(x, y));
		AffineTransform saveAT = g2D.getTransform();
		g2D.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		
		this.getShape().draw(g);
		this.getShape().rotateShape(rotationAngle, this.centerP);
		this.getShape().draw(g);
		
		g2D.setTransform(saveAT);
		
		this.previousP.x = x;
		this.previousP.y = y;
	}
	@Override
	public void continueTransforming(Graphics g, int x, int y) {

	}
	@Override
	public void finishTransforming(Vector<GEShape> shapeList) {

	}
	private double computeRotationAngle(Point startP, Point previousP, Point currentP) {
		double startAngle = Math.toDegrees(
				Math.atan2(startP.getX()-previousP.getX(), startP.getY()-previousP.getY()));
		double endAngle = Math.toDegrees(
				Math.atan2(startP.getX()-currentP.getX(), startP.getY()-currentP.getY()));
		double angle = startAngle-endAngle;
		if (angle<0) angle += 360;
		return angle;
	}
	@Override
	public boolean inValidShape(int x, int y) {	return false;}
}
