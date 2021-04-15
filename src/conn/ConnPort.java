package conn;

import java.io.IOException;

public interface ConnPort {
	
	public void close() throws IOException;
	public Object getData();

}
