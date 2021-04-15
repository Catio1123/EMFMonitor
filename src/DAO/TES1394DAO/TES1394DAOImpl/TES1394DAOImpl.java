package DAO.TES1394DAO.TES1394DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.TES1394DAO.TES1394DAO;
import PO.TES1394Bean;
import conn.database.DBConnector;


public class TES1394DAOImpl implements TES1394DAO {

	@Override
	public synchronized TES1394Bean findDataByDate(Date date, DBConnector dbConn) {
		String sql = "select * from TES1394 where date = ?";
		PreparedStatement prestmt;
		TES1394Bean data = new TES1394Bean();
		try {
			prestmt = dbConn.getPreparedStatement(sql);
			prestmt.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = prestmt.executeQuery();
			data.setDate(rs.getDate(1));
			data.setxValue(rs.getString(2));
			data.setyValue(rs.getString(3));
			data.setzValue(rs.getString(4));
			data.setXyzValue(rs.getString(5));
			data.setUnit(rs.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public synchronized ArrayList<TES1394Bean> listSpecData(Date start, Date end, DBConnector dbConn) {

		return null;
	}

	@Override
	public synchronized ArrayList<TES1394Bean> listAllData(DBConnector dbConn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized boolean insertData(TES1394Bean data, DBConnector dbConn) {
		String sql = "INSERT INTO [dbo].[TES1394]\r\n"
				+ "           ([date]\r\n"
				+ "           ,[xValue]\r\n"
				+ "           ,[yValue]\r\n"
				+ "           ,[zValue]\r\n"
				+ "           ,[xyzValue]\r\n"
				+ "           ,[unit])\r\n"
				+ "     VALUES\r\n"
				+ "           (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement prestmt = dbConn.getPreparedStatement(sql);
			prestmt.setTimestamp(1, new java.sql.Timestamp(data.getDate().getTime()));
			prestmt.setString(2, data.getxValue());
			prestmt.setString(3, data.getyValue());
			prestmt.setString(4, data.getzValue());
			prestmt.setString(5, data.getXyzValue());
			prestmt.setString(6, data.getUnit());
			prestmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public synchronized boolean deleteData(Date date, DBConnector dbConn) {
		
		
		return false;
	}

	@Override
	public synchronized boolean updateData(ArrayList<TES1394Bean> date, DBConnector dbConn) {
		// TODO Auto-generated method stub
		return false;
	}

}
