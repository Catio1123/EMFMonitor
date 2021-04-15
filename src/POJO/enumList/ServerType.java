package POJO.enumList;

import java.util.Arrays;

public enum ServerType {
	sqlserver;
	
	public static String[] getNames() {
		return Arrays.stream(ServerType.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
