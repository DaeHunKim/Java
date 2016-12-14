package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.Vector;

import constants.GEConstants;

public class GEGroup extends GERectangle{
	private static final long serialVersionUID = 1L;
	private Vector<GEShape> shapes;
	
	public GEGroup(){
		super();
		this.shapes = new Vector<GEShape>();
	}
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void addPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle tempRectangle = (Rectangle)this.getShape();
		tempRectangle.setFrameFromDiagonal(this.px, this.py, x, y);
		for(GEShape shape : this.shapes){
			shape.movePoint(x, y);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		super.moveShape(x, y);
		for(GEShape shape : this.shapes){
			shape.moveShape(x, y);
		}
	}
	public void resizeShape(int dw, int dh){
		Point resizePoint = new Point(dw,dh);
		super.resizeShape(resizePoint);
		for(GEShape shape : this.shapes){
			shape.resizeShape(resizePoint);
		}
	}
	public Vector<GEShape> getChildList() {
		return this.shapes;
	}
	public boolean addShape(GEShape shape){
		if(shape.isSelected()){
			this.shapes.add(0, shape);
			if(this.shapes.size() == 1){
				this.setShape(shape.getBounds());
			}else{
				this.setShape(getShape().getBounds().createUnion(shape.getBounds()));
			}
			return true;
			
		}
		return false;
	}
	
	// selectAll을 이용하여 모든 도형 선택시 boundary 정해주는 함수.
	public void initializeBound(){
		((RectangularShape) this.getShape()).setFrameFromDiagonal(GEConstants.GROUP_BOUNDARY_MIN, GEConstants.GROUP_BOUNDARY_MIN,
				GEConstants.GROUP_BOUNDARY_MAX, GEConstants.GROUP_BOUNDARY_MAX);
	}
	
	public boolean onShape(GEShape shape){
		if(this.getShape().contains(shape.getShape().getBounds())){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		Rectangle boundingRect = new Rectangle();
		if(shapes!=null){
			for(GEShape shape : shapes){
				shape.draw(g2D);
			}
		}
		if(this.isSelected()){
			g2D.setColor(GEConstants.COLOR_LINE_DEFAULT);
			g2D.draw(this.getShape());
		}
		if(this.isSelected()){
			for(GEShape shape : this.shapes){
				if(this.shapes.size() == 1){
					this.setShape(shape.getShape().getBounds());
				}else{
					this.setShape(getShape().getBounds().createUnion(shape.getShape().getBounds()));
				}				
			}
			g2D.draw(boundingRect);
			this.anchors.draw(g2D);
			this.anchors.setAnchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.anchors.draw(g2D);			
		}
	}
}
