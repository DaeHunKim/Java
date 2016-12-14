package menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstants;
import frames.GEDrawingPanel;

public class GEMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	public GEMenuBar(){
		super();
		// working variable
		for(GEConstants.EMenus eMenu: GEConstants.EMenus.values()) {
			JMenu menu = eMenu.newMenu();
			this.add(menu);
			menu.setText(eMenu.getName());
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		for(int i = 0; i<this.getMenuCount();i++){
			GEMenu menu = (GEMenu)this.getMenu(i);
			menu.init(drawingPanel);
		}
	}
}
