import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ToolBar() {
		//�ӽú���
		Image oldImage;
		Image newImage;
		
		//������ ����
		String fileRectangle = "rectangle.PNG";
		String fileEllipse = "ellipse.PNG";
		String fileLine = "line.PNG";
		String filePolygon = "polygon.PNG";
		
		//�̹��� ������
		ImageIcon iconRectangle = new ImageIcon(fileRectangle);
		ImageIcon iconEllipse = new ImageIcon(fileEllipse);
		ImageIcon iconLine = new ImageIcon(fileLine);
		ImageIcon iconPolygon = new ImageIcon(filePolygon);
		
		//�̹��� ũ�� ����
		oldImage = iconRectangle.getImage();
		newImage = oldImage.getScaledInstance(20, 20, 0);
		iconRectangle = new ImageIcon(newImage);
		
		oldImage = iconEllipse.getImage();
		newImage = oldImage.getScaledInstance(20, 20, 0);
		iconEllipse = new ImageIcon(newImage);
		
		oldImage = iconLine.getImage();
		newImage = oldImage.getScaledInstance(20, 20, 0);
		iconLine = new ImageIcon(newImage);
		
		oldImage = iconPolygon.getImage();
		newImage = oldImage.getScaledInstance(20, 20, 0);
		iconPolygon = new ImageIcon(newImage);
		
		//�簢��,Ÿ��,��,�ٰ���
		JButton rectangle =  new JButton(iconRectangle);
		this.add(rectangle);
		JButton ellipse =  new JButton(iconEllipse);
		this.add(ellipse);
		JButton line =  new JButton(iconLine);
		this.add(line);
		JButton polygon =  new JButton(iconPolygon);
		this.add(polygon);
	}
}
