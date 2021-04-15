package conn.database.DBurlFactory;

public class SQLServerURLFactory {
	
	public static String createURL(String ip, String port, String DBname) {
		return "jdbc:sqlserver://" + ip + ":" + port + ";" + "databaseName=" + DBname;
	}
}
