package UI.displayUI.subDisplayPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Constructor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import POJO.enumList.Device;
import POJO.enumList.LocalPort;
import UI.UIPathFactory.UIPathFactory;


public class LocDisplayPlan extends JPanel{
	
	public LocDisplayPlan() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 15, 10, 15);
		

		// grid(0, 0)
		JLabel labl1 = new JLabel("Port:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labl1, gbc);

		// grid(0, 1)
		JLabel labl2 = new JLabel("Device:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labl2, gbc);

		// grid(1, 0)
		JComboBox<String> portBox = new JComboBox<String>(LocalPort.getNames());
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(portBox, gbc);

		// grid(1, 1)
		JComboBox<String> deviceBox = new JComboBox<String>(Device.getNames());
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(deviceBox, gbc);

		// button show
		JButton btnShow = new JButton("Show");
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		add(btnShow, gbc);
		btnShow.addActionListener((e) -> {

			String UIName = UIPathFactory.getLocPortUIPath() + deviceBox.getSelectedItem() + "UI";
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

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "The selected Device is undefined", "Error",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		});

	}

}
