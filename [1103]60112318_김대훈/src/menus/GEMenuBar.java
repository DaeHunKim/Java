package menus;

import javax.swing.JMenuBar;
import frames.GEDrawingPanel;

public class GEMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private GEMenuFile menuFile;
	private GEMenuEdit menuEdit;
	private GEMenuColor menuColor;
	
	public GEMenuBar(){
		super();
		menuFile = new GEMenuFile();
		menuFile.setText("File");
		this.add(menuFile);
		
		menuEdit = new GEMenuEdit();
		menuEdit.setText("Edit");
		this.add(menuFile);
		
		menuColor = new GEMenuColor();
		menuColor.setText("Color");
		this.add(menuColor);
	}
	
	public void init(GEDrawingPanel drawingPanel){
		menuFile.init(drawingPanel);
	}
}
