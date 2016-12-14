package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import constants.GEConstants;

import menus.GEMenuBar;

public class GEMainFrame extends JFrame{
	// attribute
	private static final long serialVersionUID = 1L;
	private static GEMainFrame frame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	// component
	private GEMenuBar menuBar;
	private GEDrawingPanel drawingPanel;
	private GEToolBar toolBar;
	
	// association
	
	// working variables

	private GEMainFrame(String title){
		super(title);
		// attribute initialization 
		this.setSize(GEConstants.FRAME_W,GEConstants.FRAME_H);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		// components lifecycle management
		this.menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		this.drawingPanel= new GEDrawingPanel();
		this.add(drawingPanel);

		this.toolBar = new GEToolBar(GEConstants.TITLE_SHAPETOOLBAR);
		this.add(toolBar,BorderLayout.NORTH);
	}
	
	public static GEMainFrame getInstance(){
		return frame;
	}
	
	public void init(){
		// association 걸기
		this.menuBar.init(drawingPanel);
		this.toolBar.init(drawingPanel); //init이 GEToolBar로 변수 Cast
		this.drawingPanel.init();
	}
}
