package DAO.TES1394DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import PO.TES1394Bean;
import conn.database.DBConnector;

public interface TES1394DAO {

	TES1394Bean findDataByDate(Date date, DBConnector dbConn);
	
	ArrayList<TES1394Bean> listSpecData(Date start, Date end, DBConnector dbConn);
	ArrayList<TES1394Bean> listAllData(DBConnector dbConn);
	
	boolean insertData(TES1394Bean data, DBConnector dbConn);
	boolean deleteData(Date date, DBConnector dbConn);
	boolean updateData(ArrayList<TES1394Bean> date, DBConnector dbConn);
	
}
