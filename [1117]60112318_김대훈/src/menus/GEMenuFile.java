package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstants;
import constants.GEConstants.EFileMenuItems;
import entity.GEModel;
import frames.GEDrawingPanel;

public class GEMenuFile extends GEMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems; // 임시
	private ActionListener actionListener;
	private String currentDirectory = ".";
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
	
	public void init(GEDrawingPanel drawingPanel){
		super.init(drawingPanel);
		try {
			this.currentDirectory = (String)GEModel.read(GEConstants.SFILECONFIG);
		} catch (ClassNotFoundException | IOException e) {
			this.currentDirectory = GEConstants.SWORKSPACE;
		}
	}
	
	private void newfile() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			drawingPanel.newShapes();
		}
	    this.newFile = true;
	}

	private void close() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			drawingPanel.newShapes();
		}
	}
		
	private JFileChooser createChooser() {
	    JFileChooser chooser = new JFileChooser(this.currentDirectory);
	    FileNameExtensionFilter filter = 
	    		new FileNameExtensionFilter(GEConstants.SFILEKIND, GEConstants.SFILEEXTENSION);
	    chooser.setFileFilter(filter);
		return chooser;
	}
	
	private void open() {
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			JFileChooser chooser = createChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	this.currentDirectory = chooser.getSelectedFile().getParent();
		    	this.fileName = this.currentDirectory + "\\" + chooser.getSelectedFile().getName();
		    	this.drawingPanel.readShapes(this.fileName);
		    }
		    this.newFile = false;
		}
	}
	
	private void save() {
		if(this.newFile==false){
			this.drawingPanel.saveShapes(this.fileName);
		}else{
		    this.saveAs();
		}
	}
	
	private void saveAs() {
		JFileChooser chooser = createChooser();
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	this.currentDirectory = chooser.getSelectedFile().getParent();
	    	this.drawingPanel.saveShapes(this.currentDirectory);
	    	this.fileName = this.currentDirectory + "\\" + chooser.getSelectedFile().getName();
	    	if(!this.fileName.endsWith(GEConstants.SFILEEXTENSION)){
	    		this.fileName = this.fileName + "." +  GEConstants.SFILEEXTENSION;
	    	}
			this.drawingPanel.saveShapes(this.fileName);
			this.newFile = false;
	    }
	}
	
	private void print(){
		GEModel.print();
	}
	
	private void exit(){
		int reply = confirm();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			System.exit(1);
		}
	}

	private int confirm() {
		int reply=-1;
		if(drawingPanel.isUpdated()==true) {
			reply = JOptionPane.showConfirmDialog(null, GEConstants.SSAVEORNOT);
		}
		if(reply == JOptionPane.OK_OPTION) {
			saveAs();
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
				saveAs();
			}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
				print();
			}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
				exit();
			}
		}
	}
}