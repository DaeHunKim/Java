import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	MenuBar() {
		FileMenu fileMenu = new FileMenu();
		this.add(fileMenu);
		EditMenu EditMenu=new EditMenu();
		this.add(EditMenu);
	}
}
