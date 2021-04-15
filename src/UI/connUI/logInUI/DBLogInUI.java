package UI.connUI.logInUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import POJO.objectPointer.ObjectPtr;
import conn.database.DBConnector;

public class DBLogInUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6371347768177592429L;

	private String servertype;
	private String ip;
	private String port;
	private String DBName;
	private String connection;
	private GridBagConstraints gbc;
	private JLabel lblUser;
	private JLabel lblPwd;
	private JTextField txtUser;
	private JPasswordField pwfPwd;
	private JButton btnLog;

	public DBLogInUI(String servertype, String ip, String port, String DBName, String connection) {

		this.servertype = servertype;
		this.ip = ip;
		this.port = port;
		this.DBName = DBName;
		this.connection = connection;

		setBounds(800, 200, 300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Log in");
		setResizable(false);

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 6);
		gbc.weightx = 0.5;
		gbc.weightx = 0.5;

		// User Name
		lblUser = new JLabel("User: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(lblUser, gbc);

		txtUser = new JTextField("");
		txtUser.setColumns(10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(txtUser, gbc);

		// Password
		lblPwd = new JLabel("Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lblPwd, gbc);

		pwfPwd = new JPasswordField("");
		pwfPwd.setColumns(10);
		pwfPwd.setEchoChar('*');
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(pwfPwd, gbc);

		// Button Log in
		btnLog = new JButton("Log in");
		gbc.gridheight = 2;
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(btnLog, gbc);
		btnLog.addActionListener(this);

		setVisible(true);

	}

	// Test
	public static void main(String[] args) throws InterruptedException {
		new DBLogInUI("sqlserver", "127.0.0.1", "4004", "DemoLab", "DB_1");
		Thread.sleep(10000);
		String[] tables = ObjectPtr.getDBTableListPtr("DB_1");
		for (String table : tables) {
			System.out.println(table);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Thread DBconn = new Thread(new Runnable() {
			@Override
			public void run() {
				String pwd = new String(pwfPwd.getPassword());
				try {
					new DBConnector(servertype, ip, port, DBName, connection, (String) txtUser.getText(), pwd);
					JOptionPane.showMessageDialog(null, "Successfully connect. ", "Connection",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {

					JOptionPane.showMessageDialog(null, "Fail to  connect.", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}
		});
		DBconn.setName("Connect Database");
		DBconn.start();
		this.dispose();
	}

}
