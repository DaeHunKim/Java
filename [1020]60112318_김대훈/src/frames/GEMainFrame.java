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
	// 이 클래스의 고유 아이디가 필요하기 때문에 자꾸 만들라고 하는 것
		private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
		// attribute - 색깔,나이,사이즈
		// component
		private JMenuBar menuBar;
		private GEDrawingPanel drawingPanel;
		private GEToolBar shapeToolBar;
		//생성자 밖에 선언하는 이유 : lifecycle이 생성자에서 끝나면 안되기 때문
		//이녀석들이 부품. 부품은 연관성을 갖는데 이것을 association이라고 함
		//연관성이 있는 오브젝트. 계층구조에서 나의 자식을 component라고 함
		
		// association
		// Working Variables : 잠시잠시 썻다가 버리는 것들
		
		private GEMainFrame(String title){
			super(title);
			// attribute initialization -- 부품과 속성을 구분하는 것은 굉장히 어려운 일.. 차의 색깔은 속성, 부품은 속성X
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
		
		// association하는 부분을 분리하는 것이 굉장히 주요하기 때문에 따로 만들어주는 것이다.
		public void init(){
			shapeToolBar.init(drawingPanel); //init이 GEToolBar로 변수 Cast
			this.setVisible(true);
		}

}
