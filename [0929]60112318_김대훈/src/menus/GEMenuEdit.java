package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EEditMenuItems;
import constants.GEConstants.EMenus;

public class GEMenuEdit extends JMenu{
	public GEMenuEdit(){
		for (EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			if(btn.name()=="Redo" || btn.name() =="Delete"){
				this.addSeparator();
			}
		}
	}
}
