package POJO.decoder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;

import POJO.objectPointer.ObjectPtr;
import UI.displayUI.localPortUI.TES1394UI;
import conn.ConnPort;

public class TES1394Decoder implements Observer {

	private ConnPort port;
	private TES1394UI ui;
	private byte data;
	private byte[] buffer = new byte[13];
	private int tail = 0;

	private String xvalue;
	private String yvalue;
	private String zvalue;
	private String xyzvalue;
	private String unit;
	private static boolean flag = true;
	private Deque<Byte> buf = new ArrayDeque<>();

	private int state = 0;
	private int ignoreByte = 0;

	public static final int DATA_LENTH = 13;
	public static final int CHECK_LENGTH = 25;

	public TES1394Decoder(String port) {
		ObjectPtr.setdecoderPtr(port, this);
		this.port = (ConnPort) ObjectPtr.getLocPtr(port);
		this.ui = (TES1394UI) ObjectPtr.getUIPtr(port);
	}

	@Override
	public synchronized void update(Observable o, Object arg) {

//		System.out.println("Update");

		data = (byte) port.getData();
		getDataSet();
	}
	
	private synchronized void getDataSet() {

		if (flag) {
			if (tail < 12) {
				buf.add(data);
				tail++;
			} else {
				flag = false;
				buf.add(data);
				if (is02(buf.peek()) && is03(buf.peekLast())) {
					Object[] array1 = buf.toArray();
					decoder(array1);
				}
			}
		} else {
			buf.pop();
			buf.add(data);
			if (is02(buf.peek()) && is03(buf.peekLast())) {
				Object[] array = buf.toArray();
				decoder(array);
			}
		}
	}

	private synchronized void decoder(Object[] para) {
//		Data Set
//		|	0	|	1	|	2	|	3	|	4	|	5	|	6	|	7	|	8	|	9	|	10	|	11	|	12	|
//		|	02	|		X		|		Y		|		Z		|status	|second	|minute	|hour	|date	|03		|
//		Status Byte 8
//		|	0	|	1	|	2	|	3	|	4	|	5	|	6	|	7	|
//		|XOL	|YOL	|ZOL	|	-	|		range	|	-	|mG/µT	|

		int[] org = new int[3];
		org[0] = ((byte) para[1] & 0xff) << 8 | ((byte) para[2] & 0xff);
		org[1] = ((byte) para[3] & 0xff) << 8 | ((byte) para[4] & 0xff);
		org[2] = ((byte) para[5] & 0xff) << 8 | ((byte) para[6] & 0xff);
		byte status = (byte) para[7];
		boolean[] OL = new boolean[3];
		OL[0] = ((status & 0b1) != 0);
		OL[1] = ((status & 0b10) != 0);
		OL[2] = ((status & 0b100) != 0);

		// Range
		int range = ((status & 0b00110000) >> 4);
		int base;
		switch (range) {
		case 0:
			base = 100;
			break;
		case 1:
			base = 10;
			break;
		case 2:
			base = 0;
			break;

		default:
			base = 0;
			break;
		}
		double[] value = new double[3];
		for (int i = 0; i < 3; i++) {
			if (OL[i]) {
				value[i] = -1;
				break;
			} else {
				value[i] = org[i] / (double) base;
			}
		}

		xvalue = (value[0] == -1) ? "Overflow" : String.format("%4.2f", value[0]);
		yvalue = (value[1] == -1) ? "Overflow" : String.format("%4.2f", value[1]);
		zvalue = (value[2] == -1) ? "Overflow" : String.format("%4.2f", value[2]);

		// xyz value
		double sqSum = 0;
		for (double d : value) {
			if (d == -1) {
				sqSum = -1;
				break;
			} else {
				sqSum = sqSum + Math.pow(d, 2);
			}
		}
		xyzvalue = (sqSum == -1) ? "Overflow" : String.format("%4.2f", Math.sqrt(sqSum));

		// 1:mG 0:µT
		boolean u = (((status & 0b10000000)) != 0);
		unit = (u ? "mG" : "µT");

		setUI();
	}
	
	private void setUI() {
		ui.setXValue(xvalue);
		ui.setYValue(yvalue);
		ui.setZValue(zvalue);
		ui.setXYZValue(xyzvalue);

		ui.setXUnit(unit);
		ui.setYUnit(unit);
		ui.setZUnit(unit);
		ui.setXYZUnit(unit);
	}

	

	private boolean is02(byte b) {

		String input = String.format("%02x", b);
		return input.equals("02");
	}

	private boolean is03(byte b) {

		String input = String.format("%02x", b);
		return input.equals("03");
	}

	

	public String getXvalue() {
		return xvalue;
	}

	public String getYvalue() {
		return yvalue;
	}

	public String getZvalue() {
		return zvalue;
	}

	public String getXyzvalue() {
		return xyzvalue;
	}

	public String getUnit() {
		return unit;
	}

}
