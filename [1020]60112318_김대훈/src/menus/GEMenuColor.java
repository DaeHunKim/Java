package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EColorMenuItems;

public class GEMenuColor extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	public GEMenuColor(){
		super();
		
		for (EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
		}
	}
}
