package conn.local;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import javax.swing.JOptionPane;

import POJO.objectPointer.ObjectPtr;
import conn.ConnPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class RS232 extends Observable implements SerialPortEventListener, ConnPort{
	public static final int BAUD_RATE = 9600;
	public static final int PARITY = 0;
	public static final int DATA_BITS = 8;
	public static final int STOP_BITS = 1;
	
	private SerialPort conRS232;
	private InputStream is;
	private byte streambyte;
	private String port;
	
	
	
	public RS232(String port) {
		ObjectPtr.setLocPortPtr(port, this);;
		this.port = port;
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(port);
			if(portIdentifier.isCurrentlyOwned()) {
				JOptionPane.showMessageDialog(null, "The selected port is owned by" + portIdentifier.getCurrentOwner() + 
						".\r\n Please close it, and connect again.", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				conRS232 = (SerialPort)portIdentifier.open(this.getClass().getName(), 0);
				conRS232.setSerialPortParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY);
				is = conRS232.getInputStream();
				conRS232.addEventListener(this);
				conRS232.notifyOnDataAvailable(true);
				JOptionPane.showMessageDialog(null, "Successfully connect " + port, "Connection", JOptionPane.INFORMATION_MESSAGE);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fail to  connect the selected port.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		
	}
	@Override
	public Object getData() {
		return streambyte;
	}
	
	
	@Override
	public void serialEvent(SerialPortEvent serialEvent) {
		if(serialEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				if( (streambyte = (byte)is.read()) != -0b1 ) {
					setChanged();
					notifyObservers();
//					System.out.println("notify");
				}
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IO Exception", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void close() {
		conRS232.close();
		ObjectPtr.rmLocPtr(port);
		JOptionPane.showMessageDialog(null, "Successfully disconnect " + port, "Disconnection", JOptionPane.INFORMATION_MESSAGE);
	}

}
