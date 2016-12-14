package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EColorMenuItems;
import constants.GEConstants.EMenus;

public class GEMenuColor extends JMenu{
	public GEMenuColor(){
		for (EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
		}
	}
}
