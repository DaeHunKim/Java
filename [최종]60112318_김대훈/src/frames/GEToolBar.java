package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EButtons;

public class GEToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private ButtonGroup buttonGroup;
	private ActionListener actionListener;
	private GEDrawingPanel drawingPanel; 
	
	public GEToolBar(String label){
		super(label);
		buttonGroup = new ButtonGroup();
		actionListener = new ActionHandler();
		
		for(EButtons ebutton : EButtons.values()){
			JRadioButton rbutton  = new JRadioButton();
			rbutton.setIcon(new ImageIcon(GEConstants.IMG_URL + ebutton.getButtonImage()));
			rbutton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + ebutton.getSelectedbuttonImage()));
			
			rbutton.addActionListener(actionListener);
			rbutton.setActionCommand(ebutton.name()); // 버튼을 눌렀을 때 어떤이름이 나와라~ 라고 어떤 버튼이 눌러졌는지 확인하는거야.
			
			this.buttonGroup.add(rbutton);
			this.add(rbutton);
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		// association
		this.drawingPanel = drawingPanel;
		// association attribute		
		((JRadioButton)this.getComponent(EButtons.rectangle.ordinal())).doClick();
	}
	
	public class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(EButtons.valueOf(e.getActionCommand())); 
			drawingPanel.setCurrentTool(EButtons.valueOf(e.getActionCommand()).getShape());
		} 
		
	}
}
