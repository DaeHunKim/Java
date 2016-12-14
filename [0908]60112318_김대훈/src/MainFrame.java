import java.awt.BorderLayout;

import javax.swing.JFrame;

// MainFrame specialize JFrame
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	MainFrame() {
		// attribute initialization
		this.setTitle("GraphicEditor");
		this.setSize(400, 600);
		
		// add components
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		DrawingPanel drawingPanel = new DrawingPanel();
		this.add(drawingPanel);
		
		ToolBar toolBar = new ToolBar();
		this.add(toolBar,BorderLayout.NORTH);
		toolBar.setFloatable(false);
	}
}
