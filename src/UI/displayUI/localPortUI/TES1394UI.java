package UI.displayUI.localPortUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POJO.decoder.TES1394Decoder;
import POJO.objectPointer.ObjectPtr;
import UI.displayUI.localPortUI.wrToDatabase.WtDatabaseUI;
import conn.database.DBConnector;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;

public class TES1394UI extends JFrame {
	
	private JLabel xlabl;
	private JLabel ylabl;
	private JLabel zlabl;
	private JLabel xyzlabl;
	private JLabel model;
	
	private JTextField xValue;
	private JTextField yValue;
	private JTextField zValue;
	private JTextField xyzValue;
	
	private JTextField xUnit; 
	private JTextField yUnit; 
	private JTextField zUnit; 
	private JTextField xyzUnit; 
	
	private JPanel xPanel;
	private JPanel yPanel;
	private JPanel zPanel;
	private JPanel xyzPanel;
	
	private Font modelFont = new Font("btnCon", Font.BOLD, 35);
	private Font valueFont = new Font("btnCon", Font.BOLD, 30);
	private Font lblFont = new Font("btnCon", Font.BOLD, 30);
	private Font unitFont = new Font("btnCon", Font.BOLD, 20);
	private int valueColumnNum = 5;
	private int unitColumnNum = 2;

	public TES1394UI(String port) {
		/*
		try {
			CommPortIdentifier.getPortIdentifier(port);
			
		} catch (NoSuchPortException e) {
			JOptionPane.showMessageDialog(null, "No such port", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		*/
		ObjectPtr.setUIPortPtr(port, this);;
		setTitle("Select Model");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(1000, 100, 600, 450);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		gbc.insets = new Insets(25, 12, 25, 12);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		//Title
		model = new JLabel("TES 1394");
		model.setFont(modelFont);
		add(model, gbc);
		
	
		
		xPanel = new JPanel(new FlowLayout());
		xlabl = new JLabel("X:");
		xlabl.setFont(lblFont);
		xValue = new JTextField("x");
		xValue.setFont(valueFont);
		xValue.setColumns(valueColumnNum);
		xValue.setEditable(false);
		xUnit = new JTextField("***");
		xUnit.setFont(unitFont);
		xUnit.setColumns(unitColumnNum);
		xUnit.setEditable(false);
		xPanel.add(xlabl);
		xPanel.add(xValue);
		xPanel.add(xUnit);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(xPanel, gbc);
		
		yPanel = new JPanel(new FlowLayout());
		ylabl = new JLabel("Y:");
		ylabl.setFont(lblFont);
		yValue = new JTextField("y");
		yValue.setFont(valueFont);
		yValue.setColumns(valueColumnNum);
		yValue.setEditable(false);
		yUnit = new JTextField("***");
		yUnit.setFont(unitFont);
		yUnit.setColumns(unitColumnNum);
		yUnit.setEditable(false);
		yPanel.add(ylabl);
		yPanel.add(yValue);
		yPanel.add(yUnit);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(yPanel, gbc);

		
		zPanel = new JPanel(new FlowLayout());
		zlabl = new JLabel("Z:");
		zlabl.setFont(lblFont);
		zValue = new JTextField("z");
		zValue.setFont(valueFont);
		zValue.setColumns(valueColumnNum);
		zValue.setEditable(false);
		zUnit = new JTextField("***");
		zUnit.setFont(unitFont);
		zUnit.setColumns(unitColumnNum);
		zUnit.setEditable(false);
		zPanel.add(zlabl);
		zPanel.add(zValue);
		zPanel.add(zUnit);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(zPanel, gbc);


		xyzPanel = new JPanel(new FlowLayout());
		xyzlabl = new JLabel("XYZ:");
		xyzlabl.setFont(lblFont);
		xyzValue = new JTextField("xyz");
		xyzValue.setFont(valueFont);
		xyzValue.setColumns(valueColumnNum);
		xyzValue.setEditable(false);
		xyzUnit = new JTextField("***");
		xyzUnit.setFont(unitFont);
		xyzUnit.setColumns(unitColumnNum);
		xyzUnit.setEditable(false);
		xyzPanel.add(xyzlabl);
		xyzPanel.add(xyzValue);
		xyzPanel.add(xyzUnit);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(xyzPanel, gbc);
		
		

		Thread decoder = new Thread(() -> {
			
			Observer tes1394De = (Observer)new TES1394Decoder(port);
			Observable rs232 = (Observable)ObjectPtr.getLocPtr(port);
			rs232.addObserver(tes1394De);
		});
		decoder.start();
		
		JButton btnDb = new JButton("write into database");
		btnDb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			DBConnector dbConn = (DBConnector) ObjectPtr.getDBPtr(port);
				WtDatabaseUI wtDatabaseUI = new WtDatabaseUI(port);
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(btnDb, gbc);
		setVisible(true);
		
	}
	
	public void setXValue(String value) {
		xValue.setText(value);
	}
	public void setYValue(String value) {
		yValue.setText(value);
	}
	public void setZValue(String value) {
		zValue.setText(value);
	}
	public void setXYZValue(String value) {
		xyzValue.setText(value);
	}
	
	public void setXUnit(String xUnit) {
		this.xUnit.setText(xUnit);
	}

	public void setYUnit(String yUnit) {
		this.yUnit.setText(yUnit);
	}

	public void setZUnit(String zUnit) {
		this.zUnit.setText(zUnit);
	}

	public void setXYZUnit(String xyzUnit) {
		this.xyzUnit.setText(xyzUnit);
	}

	//Test
	public static void main(String[] args) {
		
		new TES1394UI("test");
	}

}
