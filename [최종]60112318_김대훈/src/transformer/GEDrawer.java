package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import constants.GEConstants;
import shapes.GEShape;

public class GEDrawer extends GETransformer {
	private int px, py;

	public GEDrawer(GEShape shape) {
		super(shape);
	}
	@Override
	public void initTransforming(Graphics g, int x, int y) {
		this.px = x;
		this.py = y;
		this.getShape().setPoint(x,y);
		this.getShape().addPoint(x,y);
	}
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(dashedLineStroke);
		this.getShape().draw(g);
		this.getShape().movePoint(x,y);
		this.getShape().draw(g);
	}
	@Override
	public void continueTransforming(Graphics g, int x, int y) {
		this.getShape().addPoint(x,y);
	}
	@Override
	public void finishTransforming(Vector<GEShape> shapeList) {
	}
	@Override
	public boolean inValidShape(int x, int y) {
		if(this.px==x || this.py == y){
			return false;	
		}else
			return true;
	}

}
