package conn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.objectPointer.ObjectPtr;
import conn.database.DBurlFactory.SQLServerURLFactory;



public class DBConnector {
	private String servertype;
	private String ip;
	private String port;
	private String DBName;
	private String connection;
	private String DBurl;
	private String user;
	private String password;
	private String sqlListTable;
	private Connection conn;

	public DBConnector(String servertype, String ip, String port, String DBName, String connection, String user,
			String password) throws SQLException {
		this.servertype = servertype;
		this.ip = ip;
		this.port = port;
		this.DBName = DBName;
		this.connection = connection;
		this.user = user;
		this.password = password;

		switch (servertype) {
		case "sqlserver":
			DBurl = SQLServerURLFactory.createURL(ip, port, DBName);
			break;

		default:
			break;
		}

		conn = DriverManager.getConnection(DBurl, user, password);
		ObjectPtr.setDBPortPtr(connection, this);
		getTableList();
	}


	private void getTableList() throws SQLException {
		sqlListTable = "SELECT \r\n" + " TABLE_SCHEMA, TABLE_NAME\r\n" + "FROM\r\n"
				+ "    information_schema.tables;";
		Statement stmt = conn.createStatement();
		ResultSet rsTableList = stmt.executeQuery(sqlListTable);
		ArrayList<String> tableList = new ArrayList<String>();
		String tableName = "";
		while (rsTableList.next()) {
			tableName = "[" + rsTableList.getString(1) + "].[" + rsTableList.getString(2) + "]";
			tableList.add(tableName);
		}
		String[] tables = tableList.toArray(new String[0]);
		ObjectPtr.setDBTableListPtr(connection, tables);
		
		stmt.close();
		rsTableList.close();
	}
	
	public Statement getStatement() throws SQLException {
		return conn.createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	
	public void close(String connection) throws SQLException {
		
		conn.close();
		ObjectPtr.rmDBPtr(connection);
		ObjectPtr.rmDBTableListPtr(connection);
	}
	public void refreshTableList() throws SQLException {
		getTableList();
	}
}
