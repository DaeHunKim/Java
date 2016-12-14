package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EFileMenuItems;

public class GEMenuFile extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	public GEMenuFile(){
		super();
		
		for (EFileMenuItems btn : EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			if(btn.name()=="Open" || btn.name() == "Save_as"){
				this.addSeparator();
			}
		}
	}
}