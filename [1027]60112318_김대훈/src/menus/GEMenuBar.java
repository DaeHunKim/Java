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
		JMenu menu;
		for(GEConstants.EMenus eMenu: GEConstants.EMenus.values()) {
			menu = eMenu.newMenu();
			this.add(menu);
			menu.setText(eMenu.getName());
		}
	}
}
