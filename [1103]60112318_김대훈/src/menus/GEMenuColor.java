package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EColorMenuItems;

public class GEMenuColor extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	
	public GEMenuColor(){
		super();
		
		this.actionListener = new actionHandler();
		this.menuItems = new Vector<JMenuItem>();
		
		for (EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			this.menuItems.add(menuItem);
			
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(btn.toString());
		}
	}
	public class actionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
