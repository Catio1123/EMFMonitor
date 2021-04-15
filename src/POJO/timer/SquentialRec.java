package POJO.timer;

import java.util.Timer;

import POJO.timer.localPortToDatabase.TES1394ToDatabase;


public class SquentialRec extends Timer{

	private long delay;
	private long period;
	private String port;
	
	public SquentialRec(String delay, String period, String port, String loc) {
		this.delay = Long.parseLong(delay, 10)*1000L;
		this.period = Long.parseLong(period, 10)*1000L;
		this.port = port;
		TES1394ToDatabase task = new TES1394ToDatabase(port, loc);
		schedule(task, this.delay, this.period);

	}
	
}
