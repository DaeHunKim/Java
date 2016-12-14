package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EEditMenuItems;

public class GEMenuEdit extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	public GEMenuEdit(){
		super();
		
		for (EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			if(btn.name()=="Redo" || btn.name() =="Delete"){
				this.addSeparator();
			}
		}
	}
}
