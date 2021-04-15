package POJO.enumList;

import java.util.Arrays;

public enum DBConnection {
	DB_1, DB_2, DB_3, DB_4;
	
	public static String[] getNames() {
		return Arrays.stream(DBConnection.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
