package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EFileMenuItems;
import frames.GEDrawingPanel;
import shapes.GEShape;

public class GEMenuFile extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	private GEDrawingPanel drawingPanel;
	private Vector<JMenuItem> menuItems; // 임시
	private ActionListener actionListener;
	
	public GEMenuFile(){
		super();
		this.actionListener = new FileMenuHandler();
		this.menuItems = new Vector<JMenuItem>();
		for (EFileMenuItems btn : EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
			this.menuItems.add(menuItem);
			//액션리스너 에드
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(btn.toString());
			//메뉴에 선귿기 코드
			if(btn.name()=="Open" || btn.name() == "Save_as"){
				this.addSeparator();
			}
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
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
	
	@SuppressWarnings("unchecked")
	private void open() {
		File file = new File("");
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath()+"\\60112417_조국")));
			Object obj = in.readObject();
			drawingPanel.setShapeList((Vector<GEShape>) obj);
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			try{
				if(in != null)
					in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void save() {
		File file = new File("");
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()+"\\60112417_조국")));
			System.out.println("저장경로 : "+file.getAbsolutePath() + ", 파일이름 : 60112417_조국");
			out.writeObject(drawingPanel.getShapeList());
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try{
				if(out != null) 
					out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void print(){
		PrinterJob pJob = PrinterJob.getPrinterJob();
		if (! pJob.printDialog())
			return;
			try {
				pJob.print();
			} catch (PrinterException pe) {
				System.out.println("프린터 에러 " + pe.getMessage() );
			}
	}
	
	private void exit(){
		System.exit(1);
	}
	
	public class FileMenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand().toString());
			if(e.getActionCommand().equals(EFileMenuItems.New.getName())){
				exit();
			}else if(e.getActionCommand().equals(EFileMenuItems.Open.getName())){
				open();
			}else if(e.getActionCommand().equals(EFileMenuItems.Save.getName())){
				save();
			}else if(e.getActionCommand().equals(EFileMenuItems.Save_as.getName())){
				
			}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
				print();
			}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
				exit();
			}
		}
	}
}