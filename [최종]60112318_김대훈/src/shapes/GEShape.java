package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

abstract public class GEShape implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	protected int px, py;
	public static enum EAnchors {NN, SS, EE, WW, NE, NW, SE, SW, RR, MM};
	protected GEAnchors anchors;
	public GEAnchors getAnchors() {return this.anchors;}
	private void setAnchors(GEAnchors anchors) { this.anchors = anchors;}

	protected AffineTransform affineTransform;
	
	protected EAnchors eSelectedAnchor;
	public EAnchors geteSelectedAnchor() {return this.eSelectedAnchor;}	
	
	protected Color lineColor, fillColor;
	public Color getLineColor() {return this.lineColor;}
	public Color getFillColor() {return this.fillColor;}
	public void setLineColor(Color lineColor) {	this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) {	this.fillColor = fillColor;}	

	private boolean selected;
	public boolean isSelected() {return this.selected;}
	public void setSelected(boolean selected) {
		this.selected = selected;
		if (this.selected) {
			this.anchors = new GEAnchors();
			Rectangle boundingRect = this.shape.getBounds();
			this.anchors.setAnchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
		}else{
			this.anchors = null;
		}
	}
	
	protected Shape shape; 
	public Shape getShape() {return this.shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public GEShape(Shape shape){
		this.shape = shape;
		this.affineTransform = new AffineTransform();
		this.eSelectedAnchor = null;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		if(this.fillColor != null){
			g2D.setColor(this.fillColor);
			g2D.fill(this.shape);
		}
		if(this.lineColor != null){
			g2D.setColor(this.lineColor);
			g2D.draw(this.shape);
		}
		if(this.selected){
			Rectangle boundingRect = this.shape.getBounds();
			this.anchors.draw(g2D);
			this.anchors.setAnchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.anchors.draw(g2D);
		}
	}
	
	public boolean onShape(Point p){
		if(this.selected){
			this.eSelectedAnchor = this.anchors.onAnchor(p.x, p.y);
			if(this.eSelectedAnchor !=null){
				return true;
			}
		}
		if(this.shape.intersects(p.x, p.y, 2, 2)){
			this.eSelectedAnchor = EAnchors.MM;
			return true;
		}
		return false;
	}
	
	public Rectangle getBounds(){
		return shape.getBounds();
	}
	
	public void setGraphicsAttributes(GEShape shape){
		this.setLineColor(shape.getLineColor());
		this.setFillColor(shape.getFillColor());
		this.setAnchors(shape.getAnchors());
		this.setAnchors(shape.getAnchors());
		this.setSelected(shape.isSelected());
	}
	public void moveShape(int x, int y){
		this.affineTransform.setToTranslation(x, y);
		this.setShape(this.affineTransform.createTransformedShape(this.getShape()));
	}
	public void resizeShape(Point2D resizeFactor) {
		this.affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		this.setShape(this.affineTransform.createTransformedShape(getShape()));
	}
	public void moveReverse(Point2D resizeAnchor) {
		this.affineTransform.setToTranslation(-resizeAnchor.getX(), -resizeAnchor.getY());
		this.setShape(this.affineTransform.createTransformedShape(this.getShape()));
	}	
	public void move(Point2D resizeAnchor) {
		this.affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		this.setShape(this.affineTransform.createTransformedShape(this.getShape()));
	}
	public void rotateShape(double rotationAngle, Point centerP) {
		this.affineTransform.setToRotation(Math.toRadians(rotationAngle), centerP.getX(), centerP.getY());
		this.setShape(this.affineTransform.createTransformedShape(this.getShape()));
	}
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);
	abstract public void movePoint(int x, int y);
	abstract public GEShape deepCopy();
}
