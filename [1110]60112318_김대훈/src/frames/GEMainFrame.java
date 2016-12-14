package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import constants.GEConstants;

import menus.GEMenuBar;

public class GEMainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
		private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
		// attribute
		// component
		private GEMenuBar menuBar;
		private GEDrawingPanel drawingPanel;
		private GEToolBar toolBar;

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

			toolBar = new GEToolBar(GEConstants.TITLE_SHAPETOOLBAR);
			this.add(toolBar,BorderLayout.NORTH);
		}
		
		public static GEMainFrame getInstance(){
			return frame;
		}
		
		public void init(){
			toolBar.init(drawingPanel); //init이 GEToolBar로 변수 Cast
			menuBar.init(drawingPanel);
			this.setVisible(true);
		}
}
