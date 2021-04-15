package POJO.enumList;

import java.util.Arrays;

public enum PortType {
	RS232;
	
	public static String[] getNames() {
		return Arrays.stream(PortType.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
