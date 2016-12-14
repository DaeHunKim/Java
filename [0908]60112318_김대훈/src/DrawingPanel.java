import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrawingPanel() {
		JButton button = new JButton("test");
		this.add(button,BorderLayout.NORTH);
	}
}
