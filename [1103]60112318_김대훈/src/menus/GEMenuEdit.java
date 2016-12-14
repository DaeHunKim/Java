package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EEditMenuItems;

public class GEMenuEdit extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	public GEMenuEdit(){
		super();
		
		this.actionListener = new actionHandler();
		this.menuItems = new Vector<JMenuItem>();		
		
		for (EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			this.menuItems.add(menuItem);
			
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(btn.toString());
			
			if(btn.name()=="Redo" || btn.name() =="Delete"){
				this.addSeparator();
			}
		}
	}
	
	public class actionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}
