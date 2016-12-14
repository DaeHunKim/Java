package shapes;

import java.awt.Rectangle;
import java.awt.TextField;

public class GEText extends GEShape {
	private static final long serialVersionUID = 1L;
	public GEText() {
		super(new Rectangle());
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void addPoint(int x, int y) {
		TextField text = new TextField("123");
		text.setLocation(x,y);
	}

	@Override
	public void movePoint(int x, int y) {
//		TextField text = this.getShape();
//		text.setSize(x-px, y-py);
	}

	@Override
	public void moveShape(int x, int y) {

	}

	@Override
	public GEShape deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
