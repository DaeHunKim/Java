package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Vector;

import constants.GEConstants;
import shapes.GEAnchors;
import shapes.GEShape;
import shapes.GEShape.EAnchors;

public class GEResizer extends GETransformer {
	private Point2D resizeAnchorP;
	public GEResizer(GEShape shape) {
		super(shape);
		this.previousP = new Point();
	}
	@Override
	public void initTransforming(Graphics g, int x, int y) {
		this.previousP.x = x;
		this.previousP.y = y;
		this.resizeAnchorP = getResizeAnchor();
		this.getShape().moveReverse(this.resizeAnchorP);
	}
	
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(this.dashedLineStroke);
		
		Point2D resizeFactor = computeResizeFactor(this.previousP.x, this.previousP.y, x, y);
		AffineTransform tempAffine = g2D.getTransform();
		g2D.translate(this.resizeAnchorP.getX(), this.resizeAnchorP.getY());
		
		this.getShape().draw(g);
		this.getShape().resizeShape(resizeFactor);
		this.getShape().draw(g);
		
		g2D.setTransform(tempAffine);
		
		this.previousP.x = x;
		this.previousP.y = y;
	}
	
	private Double computeResizeFactor(int previousX, int previousY, int currentX, int currentY) {
		double deltaW = 0;
		double deltaH = 0;
		
		switch (getShape().geteSelectedAnchor()) {
			case EE: deltaW=  currentX-previousX; 	deltaH=  0; 					break;
			case WW: deltaW=-(currentX-previousX);	deltaH=  0; 					break;
			case SS: deltaW=  0;					deltaH=  currentY-previousY; 	break;
			case NN: deltaW=  0;					deltaH=-(currentY-previousY); 	break;
			case SE: deltaW=  currentX-previousX; 	deltaH=  currentY-previousY;	break;
			case NE: deltaW=  currentX-previousX; 	deltaH=-(currentY-previousY);	break;
			case SW: deltaW=-(currentX-previousX);	deltaH=  currentY-previousY;	break;			
			case NW: deltaW=-(currentX-previousX);	deltaH=-(currentY-previousY);	break;
			default: break;
		}
		
		double currentW = getShape().getBounds().getWidth();
		double currentH = getShape().getBounds().getHeight();
		double xFactor = 1.0;
		if(currentW > 0.0){
			xFactor = (xFactor+deltaW/currentW);
		}
		double yFactor = 1.0;
		if(currentH > 0.0){
			yFactor = (yFactor+deltaH/currentH);
		}
		
		return new Point.Double(xFactor,yFactor);
	}

	private Point getResizeAnchor() {
		GEAnchors anchors = getShape().getAnchors();
		Point resizeAnchor = new Point();
		switch (this.getShape().geteSelectedAnchor()) {
		case EE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.WW).getX(), 	this.getShape().getBounds().getX()+this.getShape().getBounds().getWidth()+10); break;
		case WW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.EE).getX(), 	this.getShape().getBounds().getX()+this.getShape().getBounds().getWidth()+10); break;
		case SS: resizeAnchor.setLocation(this.getShape().getBounds().getX()+this.getShape().getBounds().getWidth()+10, 	anchors.getBounds(EAnchors.NN).getY()); break;
		case NN: resizeAnchor.setLocation(this.getShape().getBounds().getX()+this.getShape().getBounds().getWidth()+10, 	anchors.getBounds(EAnchors.SS).getY()); break;
		case SE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NW).getX(), 	anchors.getBounds(EAnchors.NW).getY()); break;
		case NE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SW).getX(), 	anchors.getBounds(EAnchors.SW).getY()); break;
		case SW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NE).getX(), 	anchors.getBounds(EAnchors.NE).getY()); break;
		case NW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SE).getX(), 	anchors.getBounds(EAnchors.SE).getY()); break;
		default: break;
		}
		return resizeAnchor;
	}
	
	@Override
	public void continueTransforming(Graphics g, int x, int y) {
	}
	@Override
	public void finishTransforming(Vector<GEShape> shapeList) {
		this.getShape().move(this.resizeAnchorP);
	}
	@Override
	public boolean inValidShape(int x, int y) {	return false;}
}