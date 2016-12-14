package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import constants.GEConstants;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import menus.GEMenuBar;

public class GEMainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	// �� Ŭ������ ���� ���̵� �ʿ��ϱ� ������ �ڲ� ������ �ϴ� ��
		private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
		// attribute - ����,����,������
		// component
		private JMenuBar menuBar;
		private GEDrawingPanel drawingPanel;
		private GEToolBar shapeToolBar;
		//������ �ۿ� �����ϴ� ���� : lifecycle�� �����ڿ��� ������ �ȵǱ� ����
		//�̳༮���� ��ǰ. ��ǰ�� �������� ���µ� �̰��� association�̶�� ��
		//�������� �ִ� ������Ʈ. ������������ ���� �ڽ��� component��� ��
		
		// association
		// Working Variables : ������ ���ٰ� ������ �͵�
		
		private GEMainFrame(String title){
			super(title);
			// attribute initialization -- ��ǰ�� �Ӽ��� �����ϴ� ���� ������ ����� ��.. ���� ������ �Ӽ�, ��ǰ�� �Ӽ�X
			this.setSize(GEConstants.FRAME_W,GEConstants.FRAME_H);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			// components lifecycle management
			menuBar = new GEMenuBar();
			this.setJMenuBar(menuBar);
			
			drawingPanel= new GEDrawingPanel();
			this.add(drawingPanel);

			shapeToolBar = new GEToolBar(GEConstants.TITLE_SHAPETOOLBAR);
			this.add(shapeToolBar,BorderLayout.NORTH);
		}
		
		public static GEMainFrame getInstance(){
			return frame;
		}
		
		// association�ϴ� �κ��� �и��ϴ� ���� ������ �ֿ��ϱ� ������ ���� ������ִ� ���̴�.
		public void init(){
			shapeToolBar.init(drawingPanel); //init�� GEToolBar�� ���� Cast
			this.setVisible(true);
		}

}
