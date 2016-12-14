package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import constants.GEConstants.EEditMenuItems;
import shapes.GEShape;

public class GEMenuEdit extends GEMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private Vector<GEShape> copyList;
	
	public GEMenuEdit(){
		super();
		this.actionListener = new actionHandler();
		this.copyList = new Vector<GEShape>();
		
		for (EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(btn.toString());
			if(btn.name()=="Redo" || btn.name() =="Delete"){
				this.addSeparator();
			}
		}
	}

	public void copy() {
		this.copyList.clear();
		this.copyList.addAll(this.drawingPanel.copy());
	}

	public void cut() {
		this.copyList.clear();
		this.copyList.addAll(this.drawingPanel.cut());
	}

	public void paste() {
		this.drawingPanel.paste(this.copyList);
	}
	
	public void delete() {
		this.drawingPanel.delete();
	}
	
	public void redo() {
		this.drawingPanel.redo();
	}

	public void undo() {
		this.drawingPanel.undo();
	}
	
	public void group() {
		this.drawingPanel.group();
	}

	public void unGroup() {
		this.drawingPanel.unGroup();
	}

	public void selectAll() {
		this.drawingPanel.selectAll();
	}
	
	public class actionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand().toString());
			if(e.getActionCommand().equals(EEditMenuItems.Copy.getName())){
				copy();
			}else if(e.getActionCommand().equals(EEditMenuItems.Cut.getName())){
				cut();
			}else if(e.getActionCommand().equals(EEditMenuItems.Paste.getName())){
				paste();
			}else if(e.getActionCommand().equals(EEditMenuItems.Delete.getName())){
				delete();
			}else if(e.getActionCommand().equals(EEditMenuItems.Group.getName())){
				group();
			}else if(e.getActionCommand().equals(EEditMenuItems.Ungroup.getName())){
				unGroup();
			}else if(e.getActionCommand().equals(EEditMenuItems.Redo.getName())){
				redo();
			}else if(e.getActionCommand().equals(EEditMenuItems.Undo.getName())){
				undo();
			}else if(e.getActionCommand().equals(EEditMenuItems.SelectAll.getName())){
				selectAll();
			}
		}
		
	}
}
