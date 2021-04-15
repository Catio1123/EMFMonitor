package UI.displayUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import UI.connUI.subConnPane.LocConnPane;
import UI.displayUI.subDisplayPane.LocDisplayPlan;

public class DisplayUI extends JFrame{

	
	private static final long serialVersionUID = 7541418106777575657L;
	
	public DisplayUI() {
		setBounds(620, 350, 400,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Display");
		setResizable(true);
		
		JTabbedPane tabPane = new JTabbedPane();
		JPanel tab1 = new LocDisplayPlan();
		
		tabPane.add(tab1, " Local ");
		
		setContentPane(tabPane);
		setVisible(true);
	}

}
