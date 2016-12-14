package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import constants.GEConstants;
import javax.swing.JMenuBar;

import menus.GEMenuBar;

public class GEMainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
		private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
		// attribute - 색깔,나이,사이즈
		// component
		private JMenuBar menuBar;
		private GEDrawingPanel drawingPanel;
		private GEToolBar shapeToolBar;

		private GEMainFrame(String title){
			super(title);
			// attribute initialization 
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
		
		public void init(){
			shapeToolBar.init(drawingPanel); //init이 GEToolBar로 변수 Cast
			this.setVisible(true);
		}

}
