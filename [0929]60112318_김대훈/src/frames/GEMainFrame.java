package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import constants.GEConstants;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame{
		private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
		private JMenuBar menuBar;
		private JToolBar shapeToolBar;
		
		private GEMainFrame(String title){
			super(title);
			menuBar = new GEMenuBar();
			setJMenuBar(menuBar);
			shapeToolBar = new GEToolBar(GEConstants.TITLE_SHAPETOOLBAR);
			this.add(shapeToolBar,BorderLayout.NORTH);
		}
		
		public static GEMainFrame getInstance(){
			return frame;
		}
		
		public void init(){
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setSize(GEConstants.WIDTH_MAINFRAME,GEConstants.HEIGHT_MAINFRAME);
			setVisible(true);
		}

}
