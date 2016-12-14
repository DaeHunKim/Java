package frames;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EButtons;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	private ButtonGroup group;
	public GEToolBar(String label){
		super(label);
		this.setSize(GEConstants.WIDTH_SHAPETOOLBAR, GEConstants.HEIGHT_SHAPETOOLBAR);

		group = new ButtonGroup();
		JRadioButton button1 = null;
		
		for(EButtons ebuttons : EButtons.values()){
			button1 = new JRadioButton();
			button1.setIcon(new ImageIcon("rsc/" + ebuttons.getButtonImage()));
			button1.setSelectedIcon(new ImageIcon("rsc/" + ebuttons.getSelectedbuttonImage()));
			group.add(button1);
			this.add(button1);
		}
	}

}
