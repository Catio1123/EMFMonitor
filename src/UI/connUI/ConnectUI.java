package UI.connUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import UI.connUI.subConnPane.DBConnPane;
import UI.connUI.subConnPane.LocConnPane;

public class ConnectUI extends JFrame{

	private static final long serialVersionUID = -9208666954526384219L;
	

	public ConnectUI() {
		setBounds(620, 100, 700, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Connect / Disconnect");
		setResizable(false);
		
		JTabbedPane tabPane = new JTabbedPane();
		JPanel tab1 = new LocConnPane();
		JPanel tab2 = new DBConnPane();
		
		tabPane.add(tab1, " Local ");
		tabPane.add(tab2, " Database ");
		
		setContentPane(tabPane);
		setVisible(true);
	}
	
	

	// Test
	public static void main(String[] args) {

		new ConnectUI();
	}
	
}
