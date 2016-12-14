package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape {
	private Rectangle shape;
	
	public GERectangle() {
		super(new Rectangle());
		this.shape = (Rectangle)this.getShape();
	}
	public void setDiagonal(Point p) {
		this.shape.setFrame(p.x, p.y, p.x, p.y);
	}
	public void setX2(int x) {
		this.shape.setFrame(this.shape.getX(), this.shape.getY(), x-this.shape.getX(), this.shape.getHeight());
	}
	public void setY2(int y) {
		this.shape.setFrame(this.shape.getX(), this.shape.getY(), this.shape.getWidth(), y-this.shape.getY());
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		// 이녀석들은 Rectangle이 가지고 있는 변수 갖다 쓰기만 하자
	}
	public int getShapeType(){
		return 1;
	}
}


