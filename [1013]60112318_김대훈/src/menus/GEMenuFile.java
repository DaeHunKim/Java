package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EFileMenuItems;
import constants.GEConstants.EMenus;

public class GEMenuFile extends JMenu{
	public GEMenuFile(){
		for (EFileMenuItems btn : EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			if(btn.name()=="Open" || btn.name() == "Save_as"){
				this.addSeparator();
			}
		}
	}
}