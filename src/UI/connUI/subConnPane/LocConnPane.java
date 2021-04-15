package UI.connUI.subConnPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import POJO.enumList.LocalPort;
import POJO.enumList.PortType;
import POJO.objectPointer.ObjectPtr;
import conn.ConnPort;
import conn.local.RS232;

public class LocConnPane extends JPanel {

	private static final long serialVersionUID = -1146359856929508496L;

	private JPanel portTypePanel;
	private JPanel portPanel;
	private JPanel connPane;
	private JPanel disPortPanel;
	private JPanel disConnPane;
	private JLabel portLabl;
	private JLabel portTypeLabl;
	private JLabel disPortLabl;
	private JButton btnCon;
	private JButton btnDis;
	private JComboBox<String> portTypeBox;
	private JComboBox<String> localPortBox;
	private JComboBox<String> disLocalPortBox;
	private GridBagConstraints connGbc;
	private GridBagConstraints mainGbc;
	private GridBagConstraints disGbc;

	private Border line = BorderFactory.createLineBorder(Color.black);

	public LocConnPane() {

		mainGbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		// Connent Panel
		connGbc = new GridBagConstraints();
		connPane = new JPanel();
		connPane.setBorder(BorderFactory.createTitledBorder(line, "Connect"));
		connPane.setLayout(new GridBagLayout());
		connGbc.anchor = GridBagConstraints.WEST;
		connGbc.insets = new Insets(5, 20, 5, 20);
		connGbc.weightx = 0.5;
		connGbc.weighty = 0.5;

		// Port select of Connent Panel
		portPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		portLabl = new JLabel("Port: ");
		localPortBox = new JComboBox<>(LocalPort.getNames());
		portPanel.add(portLabl);
		portPanel.add(localPortBox);

		connGbc.gridx = 0;
		connGbc.gridy = 0;
		connPane.add(portPanel, connGbc);

		// Port type select of Connect Panel
		portTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		portTypeLabl = new JLabel("Port type: ");
		portTypeBox = new JComboBox<String>(PortType.getNames());
		portTypePanel.add(portTypeLabl);
		portTypePanel.add(portTypeBox);

		connGbc.gridx = 0;
		connGbc.gridy = 1;
		connPane.add(portTypePanel, connGbc);

		// Connect button of connenct panel
		btnCon = new JButton("Connect");
		btnCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread localConn = new Thread(new Runnable() {
					
					@Override
					public void run() {
						String selPort = (String) localPortBox.getSelectedItem();
						new RS232(selPort);
						
					}
				});
				localConn.setName("LOCAL PORT CONNECTION");
				localConn.start();
				
			}
		});
		connGbc.gridx = 0;
		connGbc.gridy = 2;
		connGbc.fill = GridBagConstraints.BOTH;
		connPane.add(btnCon, connGbc);

		// Add connect panel to main panel
		mainGbc.fill = GridBagConstraints.BOTH;
		mainGbc.insets = new Insets(20, 30, 20, 30);
		mainGbc.gridx = 0;
		mainGbc.gridy = 0;
		add(connPane, mainGbc);

		// Disconnect Panel
		disGbc = new GridBagConstraints();
		disGbc.insets = new Insets(5, 20, 5, 20);
		disGbc.weightx = 0.5;
		disGbc.weighty = 0.5;
		disConnPane = new JPanel();
		disConnPane.setBorder(BorderFactory.createTitledBorder(line, "Disconnect"));
		disConnPane.setLayout(new GridBagLayout());

		// Port select of disconnect panel
		disPortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		disPortLabl = new JLabel("Port: ");
		disLocalPortBox = new JComboBox<>(LocalPort.getNames());
		disPortPanel.add(disPortLabl);
		disPortPanel.add(disLocalPortBox);

		disGbc.gridx = 0;
		disGbc.gridy = 0;
		disConnPane.add(disPortPanel, disGbc);

		// Disconnect button of connenct panel
		btnDis = new JButton("Disconnect");
		btnDis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String disSelPort = (String) disLocalPortBox.getSelectedItem();
				ConnPort port = (ConnPort)ObjectPtr.getLocPtr(disSelPort);
				try {
					port.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		disGbc.gridx = 0;
		disGbc.gridy = 1;
		disGbc.fill = GridBagConstraints.HORIZONTAL;
		disConnPane.add(btnDis, disGbc);

		// Add disconnect panel to main panel
		mainGbc.gridx = 1;
		mainGbc.gridy = 0;
		add(disConnPane, mainGbc);

	}
	//Test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTabbedPane tabPane = new JTabbedPane();
		JPanel locl = new LocConnPane();

		tabPane.addTab("local", locl);
		test.setContentPane(tabPane);

		test.setBounds(620, 320, 700, 350);

		test.setVisible(true);
	}
}
