package POJO.objectPointer;

import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class ObjectPtr {
	
	public static final String UI = "UI";
	
	private static Map<String, Object> localPortPtr= new HashMap<>();
	private static Map<String, Object> webPortPtr= new HashMap<>();
	private static Map<String, Object> DBPortPtr= new HashMap<>();
	private static Map<String, JFrame> UIPtr = new HashMap<>();
	private static Map<String, ByteArrayOutputStream> inputStreamClonPtr = new HashMap<>();
	private static Map<String, String[]> DBTableListPtr = new HashMap<>();
	private static Map<String, Object> decoderPtr = new HashMap<String, Object>();
	//set
	public static void setLocPortPtr(String port, Object locPort) {
		localPortPtr.put(port, locPort);
	}
	public static void setWebPortPtr(String port, Object webPort) {
		webPortPtr.put(port, webPort);
	}
	public static void setDBPortPtr(String port, Object DBPort) {
		DBPortPtr.put(port, DBPort);
	}
	public static void setdecoderPtr(String port, Object decoder) {
		decoderPtr.put(port, decoder);
	}
	public static void setUIPortPtr(String name, JFrame UI) {
		UIPtr.put(name, UI);
	}
	public static void setInputStreamClontPtr(String conn, ByteArrayOutputStream baos) {
		inputStreamClonPtr.put(conn, baos);
	}
	public static void setDBTableListPtr(String conn, String[] tableList) {
		DBTableListPtr.put(conn, tableList);
	}

	
	//get
	public static Object getLocPtr(String port) {
		return localPortPtr.get(port);
	}
	public static Object getWebPtr(String port) {
		return webPortPtr.get(port);
	}
	public static Object getDBPtr(String port) {
		return DBPortPtr.get(port);
	}
	public static Object getUIPtr(String port) {
		return UIPtr.get(port);
	}
	public static Object getdecoderPtr(String port) {
		return decoderPtr.get(port);
	}
	public static ByteArrayOutputStream getinputStreamClontPtr(String conn) {
		return inputStreamClonPtr.get(conn);
	}
	public static String[] getDBTableListPtr(String conn) {
		return DBTableListPtr.get(conn);
	}
	
	//remove
	public static void rmLocPtr(String port) {
		localPortPtr.remove(port);
	}
	public static void rmWebPtr(String port) {
		webPortPtr.remove(port);
	}
	public static void rmDBPtr(String port) {
		DBPortPtr.remove(port);
	}
	public static void rmDecoderPtr(String port) {
		decoderPtr.remove(port);
	}
	public static void rmUIPtr(String name) {
		UIPtr.remove(name);
	}
	public static void rmInputStreamClonPtr(String conn) {
		inputStreamClonPtr.remove(conn);
	}
	public static void rmDBTableListPtr(String conn) {
		DBTableListPtr.remove(conn);
	}
}
	
