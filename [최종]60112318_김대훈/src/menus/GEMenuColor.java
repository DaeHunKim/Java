package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EColorMenuItems;

public class GEMenuColor extends GEMenu{
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

	public void setLineColor(){
		Color lineColor = JColorChooser.showDialog(null, GEConstants.LINECOLOR_TITLE, null);
		if(lineColor != null){
			this.drawingPanel.setLineColor(lineColor);
		}
	}
	public void resetLineColor() {
		drawingPanel.setLineColor(GEConstants.COLOR_LINE_DEFAULT);
	}
	
	public void setFillColor(){
		Color fillColor = JColorChooser.showDialog(null, GEConstants.FILLCOLOR_TITLE, null);
		if(fillColor != null){
			this.drawingPanel.setFillColor(fillColor);
		}
	}
	public void resetFillColor() {
		drawingPanel.setFillColor(GEConstants.COLOR_FILL_DEFAULT);
	}

	
	public class actionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EColorMenuItems.FillColor.getName())){
				setFillColor();
			}else if(e.getActionCommand().equals(EColorMenuItems.ResetFillColor.getName())){
				resetFillColor();
			}else if(e.getActionCommand().equals(EColorMenuItems.LineColor.getName())){
				setLineColor();
			}else if(e.getActionCommand().equals(EColorMenuItems.ResetLineColor.getName())){
				resetLineColor();
			}else if(e.getActionCommand().equals(EColorMenuItems.TextColor.getName())){
			}
		}
	}
}
