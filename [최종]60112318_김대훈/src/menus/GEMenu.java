package menus;

import javax.swing.JMenu;

import frames.GEDrawingPanel;

public class GEMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	protected GEDrawingPanel drawingPanel;
	
	public GEMenu(){
		super();
		this.drawingPanel = null;
	}
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
}
