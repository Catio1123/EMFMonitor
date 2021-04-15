package POJO.enumList;

import java.util.Arrays;

public enum Device {
	TES1394;
	
	public static String[] getNames() {
		
		return Arrays.stream(Device.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	
}
