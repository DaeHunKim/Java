package menus;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.awt.PrintJob;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import constants.GEConstants.EFileMenuItems;

public class GEMenuFile extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
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
	
	public void invokeMethod(){
		try {
			this.getClass().getMethod("exit").invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private void exit(){
		System.exit(1);
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
	
	public class FileMenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand().toString());
			switch(EFileMenuItems.valueOf(e.getActionCommand())){
			case New :
				break;
			case Open :
				break;
			case Close:
				break;
			case Save:
				break;
			case Save_as:
				break;
			case Print :
				print();
				break;
			case Exit : 
				exit();
				break;
			default:
				break;
			}
			

		}
		
	}
}