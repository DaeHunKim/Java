import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	FileMenu() {
		super("File");
		
		//object creation
		JMenuItem newFile = new JMenuItem("New");
		//object registration to its parent
		this.add(newFile);
		
		JMenuItem open=new JMenuItem("Open");
		this.add(open);
		
		this.addSeparator();
		
		JMenuItem save=new JMenuItem("Save");
		this.add(save);
		
		JMenuItem saveAs=new JMenuItem("SaveAs");
		this.add(saveAs);
		
		this.addSeparator();
		
		JMenuItem exit=new JMenuItem("Exit");
		this.add(exit);
	}
}
