package utility;

import java.io.IOException;

import judge.frmJudge;

/**
 * 
 * @author NhanNT
 *
 */
public class Formatter {
	/**
	 * Format a source code directly Copy content of original source, then store the
	 * copy into the same path with the original source
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void Format(String path, String type, frmJudge parent) throws IOException {
		String cmd = "";
//		frmJudge parent = new frmJudge();
		if (type.equals("c") || type.equals("cpp")) {
			cmd = parent.astylePath + " --style=allman " + path;
		} else if (type.equals("java")) {
			cmd = parent.astylePath + " --style=java " + path;
		}
		Process process = Runtime.getRuntime().exec(cmd);
		while (process.isAlive()) {
		}
		process.destroy();
	}
}
