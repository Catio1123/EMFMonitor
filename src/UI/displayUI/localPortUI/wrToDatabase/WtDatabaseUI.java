package UI.displayUI.localPortUI.wrToDatabase;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POJO.enumList.DBConnection;
import POJO.enumList.LocalPort;
import POJO.timer.SquentialRec;



public class WtDatabaseUI extends JFrame {

	private Timer rec;

	public WtDatabaseUI(String port) {

		setBounds(800, 200, 100, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDelay = new JLabel("Delay");
		add(lblDelay);
		JTextField txtDelay = new JTextField("0");
		txtDelay.setColumns(5);
		add(txtDelay);
		JLabel lblperiod = new JLabel("Period");
		add(lblperiod);
		JTextField txtPeriod = new JTextField("5");
		txtPeriod.setColumns(5);
		add(txtPeriod);
		JComboBox<String> boxCon = new JComboBox<>(DBConnection.getNames());
		add(boxCon);
		JComboBox<String> boxPort = new JComboBox<String>(LocalPort.getNames());
		add(boxPort);

		JButton btnStar = new JButton("Start");
		btnStar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rec = new SquentialRec(txtDelay.getText(), txtPeriod.getText(), (String)boxCon.getSelectedItem(), (String)boxPort.getSelectedItem());
			}
		});
		
		add(btnStar);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rec.cancel();
			}
		});
		add(btnStop);
		setVisible(true);

	}

	// test
	public static void main(String[] args) {
		new WtDatabaseUI("COM1");
	}
}
