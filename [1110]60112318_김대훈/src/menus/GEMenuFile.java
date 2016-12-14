package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstants.EFileMenuItems;
import entity.GEModelShape;

public class GEMenuFile extends GEMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems; // 임시
	private ActionListener actionListener;
	private boolean newFile;
	private String fileName = null;
	
	public GEMenuFile(){
		super();
		this.actionListener = new FileMenuHandler();
		this.menuItems = new Vector<JMenuItem>();
		this.newFile = true;
		for (EFileMenuItems btn : EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			this.menuItems.add(menuItem);
			//액션리스너 에드
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(btn.toString());
			//메뉴에 선귿기 코드
			if(btn.name()=="Close" || btn.name() == "Save_as"){
				this.addSeparator();
			}
		}
	}
	
	public void invokeMethod(){
		try {
			this.getClass().getMethod("exit").invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

	private void newfile() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			drawingPanel.clearShapes();
		}
	    this.newFile = true;
	}

	private void close() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			drawingPanel.clearShapes();
		}
	}
		
	private void open() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
		    JFileChooser chooser = new JFileChooser(".");
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	this.fileName = chooser.getSelectedFile().getName();
				GEModelShape.read(this.fileName);
				this.drawingPanel.repaint();	
		    }
		}
	    this.newFile = false;
	}
	
	private void save() {
		if(this.newFile==false){
	    	GEModelShape.save(this.fileName);
			this.drawingPanel.setDirty(false);
		}else{
		    this.save_as();
		}
	}
	
	private void save_as() {
	    JFileChooser chooser = new JFileChooser(".");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	this.fileName= chooser.getSelectedFile().getName();
			GEModelShape.save(this.fileName);
			this.drawingPanel.setDirty(false);
	    }
	    this.newFile = false;
	}
	
	private void print(){
		GEModelShape.print();
	}
	
	private void exit(){
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			System.exit(1);
		}
	}

	private int confirm() {
		int reply=-1;
		if(drawingPanel.isDirty()==true) {
			reply = JOptionPane.showConfirmDialog(null, "변경 내용을 저장하시겠습니까?");
		}
		if(reply == JOptionPane.OK_OPTION) {
			save_as();
		}
		return reply;
	}
	
	private class FileMenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand().toString());
			if(e.getActionCommand().equals(EFileMenuItems.New.getName())){
				newfile();
			}else if(e.getActionCommand().equals(EFileMenuItems.Open.getName())){
				open();
			}else if(e.getActionCommand().equals(EFileMenuItems.Close.getName())){
				close();
			}else if(e.getActionCommand().equals(EFileMenuItems.Save.getName())){
				save();
			}else if(e.getActionCommand().equals(EFileMenuItems.Save_as.getName())){
				save_as();
			}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
				print();
			}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
				exit();
			}
		}
	}
}