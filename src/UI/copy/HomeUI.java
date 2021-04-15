package UI.copy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import UI.connUI.ConnectUI;
import UI.displayUI.DisplayUI;

public class HomeUI extends JFrame {

	private static final long serialVersionUID = 1193552078511993682L;

	private Font btnFont = new Font("btnCon", Font.BOLD, 16);
	private JButton btnCon;
	private JButton btnRT;
	private JButton btnHis;

	public HomeUI() {

		setBounds(200, 100, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// same constrain
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(25, 12, 25, 12);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// button 1 Connect
		btnCon = new JButton("Connect / Disconnect");
		gbc.gridy = 0;
		btnCon.setFont(btnFont);
		btnCon.addActionListener((event) -> {

			new ConnectUI();
		});
		add(btnCon, gbc);

		// button 2 display
		btnRT = new JButton("Display");
		gbc.gridy = 1;
		btnRT.setFont(btnFont);
		btnRT.addActionListener((event) -> {

			new DisplayUI();
		});
		add(btnRT, gbc);


		setVisible(true);
	}

	// Test
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new HomeUI();
		});
	}
}
