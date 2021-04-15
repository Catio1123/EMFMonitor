package UI.connUI.subConnPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import POJO.enumList.DBConnection;
import POJO.enumList.ServerType;
import POJO.objectPointer.ObjectPtr;
import UI.connUI.logInUI.DBLogInUI;
import conn.database.DBConnector;

public class DBConnPane extends JPanel {

	private static final long serialVersionUID = -3801591889471428974L;
	
	private JPanel connPane;
	private JPanel disConnPane;
	private JPanel disConnectionPane;
	private JLabel svrTypeLabl;
	private JLabel ipLabl;
	private JLabel portLabl;
	private JLabel DBNameLabl;
	private JLabel asLabl;
	private JLabel disConnectionLabl;
	private JButton btnDis;
	private JButton btnCon;
	private JTextField IPtext;
	private JTextField Porttext ;
	private JTextField DBNametext;
	private JComboBox<String> svrTypeBox;
	private JComboBox<String> asBox;
	private JComboBox<String> disConnectionBox;
	private GridBagConstraints mainGbc;
	private GridBagConstraints connGbc;
	private GridBagConstraints disGbc;

	private Border line = BorderFactory.createLineBorder(Color.black);

	public DBConnPane() {

		mainGbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		// Connect Panel
		connGbc = new GridBagConstraints();
		connPane = new JPanel();
		connPane.setBorder(BorderFactory.createTitledBorder(line, "Connect"));
		connPane.setLayout(new GridBagLayout());
		connGbc.anchor = GridBagConstraints.WEST;
		connGbc.insets = new Insets(5, 10, 5, 15);
		connGbc.weightx = 0.5;
		connGbc.weighty = 0.5;

		// label of Connect panel
		svrTypeLabl = new JLabel("Server Type: ");
		connGbc.gridx = 0;
		connGbc.gridy = 0;
		connPane.add(svrTypeLabl, connGbc);

		ipLabl = new JLabel("IP: ");
		connGbc.gridx = 0;
		connGbc.gridy = 1;
		connPane.add(ipLabl, connGbc);

		portLabl = new JLabel("Port: ");
		connGbc.gridx = 0;
		connGbc.gridy = 2;
		connPane.add(portLabl, connGbc);

		DBNameLabl = new JLabel("DB Name: ");
		connGbc.gridx = 0;
		connGbc.gridy = 3;
		connPane.add(DBNameLabl, connGbc);

		asLabl = new JLabel("As: ");
		connGbc.gridx = 0;
		connGbc.gridy = 4;
		connPane.add(asLabl, connGbc);

		// Select section of Connect Panel
		svrTypeBox = new JComboBox<>(ServerType.getNames());
		connGbc.gridx = 1;
		connGbc.gridy = 0;
		connPane.add(svrTypeBox, connGbc);

		IPtext = new JTextField("");
		IPtext.setColumns(10);
		connGbc.gridx = 1;
		connGbc.gridy = 1;
		connPane.add(IPtext, connGbc);

		Porttext = new JTextField("");
		Porttext.setColumns(10);
		connGbc.gridx = 1;
		connGbc.gridy = 2;
		connPane.add(Porttext, connGbc);

		DBNametext = new JTextField("");
		DBNametext.setColumns(10);
		connGbc.gridx = 1;
		connGbc.gridy = 3;
		connPane.add(DBNametext, connGbc);

		asBox = new JComboBox<>(DBConnection.getNames());
		connGbc.gridx = 1;
		connGbc.gridy = 4;
		connPane.add(asBox, connGbc);

		// Connect button of connect panel
		btnCon = new JButton("Connect");
		connGbc.fill = GridBagConstraints.BOTH;
		connGbc.insets = new Insets(5, 40, 5, 40);
		connGbc.gridx = 0;
		connGbc.gridy = 5;
		connGbc.gridwidth = 2;
		connPane.add(btnCon, connGbc);
		btnCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DBLogInUI((String) svrTypeBox.getSelectedItem(), 
						IPtext.getText(), Porttext.getText(), DBNametext.getText(),
						(String)asBox.getSelectedItem());
			}
		});

		// Add connPane to main Panel
		mainGbc.fill = GridBagConstraints.HORIZONTAL;
		mainGbc.insets = new Insets(20, 15, 20, 15);
		mainGbc.gridx = 0;
		mainGbc.gridy = 0;
		mainGbc.weightx = 1;
		add(connPane, mainGbc);

		// Disconnect Panel
		disGbc = new GridBagConstraints();
		disGbc.insets = new Insets(5, 0, 5, 0);
		disGbc.weightx = 0.5;
		disGbc.weighty = 0.5;
		disConnPane = new JPanel();
		disConnPane.setBorder(BorderFactory.createTitledBorder(line, "Disconnect"));
		disConnPane.setLayout(new GridBagLayout());

		// Left Side of Disconnect Panel
		disConnectionPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		disConnectionLabl = new JLabel("Connection: ");
		disConnectionBox = new JComboBox<>(DBConnection.getNames());
		disConnectionPane.add(disConnectionLabl);
		disConnectionPane.add(disConnectionBox);

		disGbc.gridx = 0;
		disGbc.gridy = 0;
		disConnPane.add(disConnectionPane, disGbc);

		// Right Side of Disconnect Panel
		btnDis = new JButton("Disconnect");
		disGbc.insets = new Insets(70, 20, 70, 20);
		disGbc.fill = GridBagConstraints.BOTH;
		disGbc.gridx = 1;
		disGbc.gridy = 0;
		disConnPane.add(btnDis, disGbc);
		btnDis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnector closeConn = (DBConnector)ObjectPtr.getDBPtr((String)disConnectionBox.getSelectedItem());
				try {
					closeConn.close((String) disConnectionBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Successfully disconnect. ", "Disconnection",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Fail to  disconnect.", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		// Add Disconnect Panel to main Panel
		mainGbc.fill = GridBagConstraints.BOTH;
		mainGbc.insets = new Insets(20, 15, 20, 15);
		mainGbc.gridx = 1;
		mainGbc.gridy = 0;
		mainGbc.weightx = 0.3;
		add(disConnPane, mainGbc);
	}

	// Test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTabbedPane tabPane = new JTabbedPane();
		JPanel DBconn = new DBConnPane();

		tabPane.addTab("Database", DBconn);
		test.setContentPane(tabPane);

		test.setBounds(620, 320, 700, 350);

		test.setVisible(true);
	}

}
