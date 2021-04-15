package POJO.enumList;

import java.util.Arrays;

public enum LocalPort {
	
	COM1, COM2, COM3, COM4, COM5, COM6, COM7, COM8;
	
	public static String[] getNames() {
		return Arrays.stream(LocalPort.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

}
