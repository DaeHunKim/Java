package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	private static final long serialVersionUID = 1L;
	private Line2D shape;
	
	public GELine(){
		super(new Line2D.Double());
		this.shape = (Line2D)this.getShape();
	}
	public void setDiagonal(Point p){
		this.shape.setLine(p.x, p.y, p.x, p.y);
	}
	public void setX2(int x) {
		this.shape.setLine(this.shape.getX1(), this.shape.getY1(), x, this.shape.getY2());
	}
	public void setY2(int y) {
		this.shape.setLine(this.shape.getX1(), this.shape.getY1(), this.shape.getX2(), y);
	}
	
	public void draw(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape); // �̳༮���� Rectangle�� ������ �ִ� ���� ���� ���⸸ ����
	}
	public int getShapeType(){
		return 3;
	}
}
