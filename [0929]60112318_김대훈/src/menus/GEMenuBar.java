package menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstants;
import constants.GEConstants.EMenus;

public class GEMenuBar extends JMenuBar{
	public GEMenuBar(){
		for(GEConstants.EMenus eMenu: GEConstants.EMenus.values()) {
			JMenu menu = eMenu.getMenu();
			this.add(menu);
			menu.setText(eMenu.getMenuName());
		}
	}
}
