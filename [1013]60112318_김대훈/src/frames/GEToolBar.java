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
	private ButtonGroup group;
	private ActionListener actionListener;
	private GEDrawingPanel drawingPanel;
	
	public GEToolBar(String label){
		super(label);
		group = new ButtonGroup();
		actionListener = new ActionHandler();
		JRadioButton rbutton = null;
		
		for(EButtons ebutton : EButtons.values()){
			rbutton = new JRadioButton();
			rbutton.setIcon(new ImageIcon(GEConstants.IMG_URL + ebutton.getButtonImage()));
			rbutton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + ebutton.getSelectedbuttonImage()));
			rbutton.addActionListener(actionListener);
			rbutton.setActionCommand(ebutton.name());
			//툴바버튼에 액션핸들러 달기
			group.add(rbutton);
			this.add(rbutton);
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
		this.setSize(GEConstants.WIDTH_SHAPETOOLBAR, GEConstants.HEIGHT_SHAPETOOLBAR);
		clickDefaultButton();
	}
	
	private void clickDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(EButtons.rectangle.ordinal());
		rButton.doClick();
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(EButtons.valueOf(e.getActionCommand()).toString()); // 입력은 확인 OK
//			GEShape shapeTool = EButtons.valueOf(e.getActionCommand()).getShape(); // 생성은 Enum의 new 메소드 가져오면 됨
//			drawingPanel.setShapeTool(shapeTool);
		}
		
	}

}
