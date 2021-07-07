package utility;

import java.io.IOException;

/**
 * 
 * @author NhanNT
 *
 */
public class Formatter {
	/**
	 * Format a source code directly
	 * Copy content of original source, then store the copy into the same path with the original source
	 * @param path
	 * @throws IOException
	 */
	public static void Format(String path) throws IOException {
            Runtime r = Runtime.getRuntime();
		Process process = r.exec("AStyle.exe --style=allman " + path);
		while (process.isAlive()) {
			
		}
                process.destroy();
	}
}
