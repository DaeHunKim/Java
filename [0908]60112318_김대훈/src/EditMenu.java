import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditMenu(){
		super("Edit");
		JMenuItem cut = new JMenuItem("cut");
		this.add(cut);
		JMenuItem copy = new JMenuItem("copy");
		this.add(copy);
		JMenuItem paste = new JMenuItem("paste");
		this.add(paste);
		this.addSeparator();
		JMenuItem delete = new JMenuItem("delete");
		this.add(delete);
		this.addSeparator();
		JMenuItem redo = new JMenuItem("redo");
		this.add(redo);
		JMenuItem undo = new JMenuItem("undo");
		this.add(undo);
		this.addSeparator();
		JMenuItem group = new JMenuItem("group");
		this.add(group);
		JMenuItem unGroup = new JMenuItem("unGroup");
		this.add(unGroup);
	}
	
}
