package UI.displayUI.localPortUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import POJO.enumList.Device;
import POJO.enumList.LocalPort;

public class RealTimeUI {

	public RealTimeUI() {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SelectRTFrame();
			}
		});
	}

	// Select Model Frame(Inner class)
	private class SelectRTFrame extends JFrame {
		JLabel labl1;
		JLabel labl2;
		JComboBox<String> portBox;
		JComboBox<String> deviceBox;
		JButton btnShow;

		SelectRTFrame() {
			setTitle("Select Model");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			setBounds(620, 320, 350, 150);
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(10, 15, 10, 15);

			// grid(0, 0)
			labl1 = new JLabel("Port:");
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(labl1, gbc);

			// grid(0, 1)
			labl2 = new JLabel("Device:");
			gbc.gridx = 0;
			gbc.gridy = 1;
			add(labl2, gbc);

			// grid(1, 0)
			portBox = new JComboBox<String>(LocalPort.getNames());
			gbc.gridx = 1;
			gbc.gridy = 0;
			add(portBox, gbc);

			// grid(1, 1)
			deviceBox = new JComboBox<String>(Device.getNames());
			gbc.gridx = 1;
			gbc.gridy = 1;
			add(deviceBox, gbc);

			// button show
			btnShow = new JButton("Show");
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			add(btnShow, gbc);
			btnShow.addActionListener((e) -> {

				String UIName = "POJO.UI." + deviceBox.getSelectedItem() + "UI";
				try {
					Class<?> clazz = Class.forName(UIName);
					Constructor<?> ctor = clazz.getConstructor(String.class);
					new Thread(() -> {
						try {
							ctor.newInstance((String) portBox.getSelectedItem());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Fail to construct UI", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}).start();
					this.dispose();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "The selected Device is undefined", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			});

			setVisible(true);
		}

	}

	// Test
	public static void main(String[] args) {
		new RealTimeUI();
		
	}
}
