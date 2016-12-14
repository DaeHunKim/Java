package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import constants.GEConstants;
import shapes.GEShape;

public class GEMover extends GETransformer {
	private int px, py;
	public GEMover(GEShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(Graphics g, int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		g2D.setStroke(dashedLineStroke);
		this.getShape().draw(g);
		this.getShape().moveShape(x-px, y-py);
		this.getShape().draw(g);
		this.px = x;
		this.py = y;
	}
	@Override
	public void continueTransforming(Graphics g, int x, int y) {
	}
	@Override
	public void finishTransforming(Vector<GEShape> shapeList) {
	}

	@Override
	public boolean inValidShape(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
