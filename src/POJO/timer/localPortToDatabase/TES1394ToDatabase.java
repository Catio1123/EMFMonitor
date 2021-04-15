package POJO.timer.localPortToDatabase;

import java.util.Date;
import java.util.TimerTask;

import DAO.DAOFactory;
import DAO.TES1394DAO.TES1394DAO;
import PO.TES1394Bean;
import POJO.decoder.TES1394Decoder;
import POJO.objectPointer.ObjectPtr;
import conn.database.DBConnector;


public class TES1394ToDatabase extends TimerTask{
	
	private String port;
	private DBConnector dbConn;
	private TES1394Decoder decoder;
	private TES1394DAO dao;
	private TES1394Bean data;
	
	
	public TES1394ToDatabase(String port, String loc) {
		this.port = port;
//		System.out.println("port:" + port);
		this.dbConn = (DBConnector)ObjectPtr.getDBPtr(port);
		this.decoder = (TES1394Decoder)ObjectPtr.getdecoderPtr(loc);
		
		
		
		dao = DAOFactory.getTES1394DAO();
		
	}
	@Override
	public void run() {
		Date now = new Date(System.currentTimeMillis());
		data = new TES1394Bean();
		data.setDate(now);
		data.setxValue(this.decoder.getXvalue());
		data.setyValue(this.decoder.getYvalue());
		data.setzValue(this.decoder.getZvalue());
		data.setXyzValue(this.decoder.getXyzvalue());
		data.setUnit(this.decoder.getUnit());
		this.dbConn = (DBConnector)ObjectPtr.getDBPtr(port);
		dao.insertData(data, dbConn);
	}
	
}
