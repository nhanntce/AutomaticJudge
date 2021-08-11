package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import judge.FileHandle;

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
	 * @param path   of file need to format
	 * @param type   type of source code
	 * @param parent Main frame
	 * @throws IOException if file not exists
	 */
	public static void Format(String path, String type, frmJudge parent) throws IOException {
		if ("py".equals(type)) {
			return;
		}
		String cmd = "";
//		frmJudge parent = new frmJudge();
		if (type.equals("c") || type.equals("cpp")) {
			cmd = parent.astylePath + " --style=allman \"" + path + "\"";
		} else {
			cmd = parent.astylePath + " --style=java \"" + path + "\"";
		}
		Process process = Runtime.getRuntime().exec(cmd);
		while (process.isAlive()) {
		}
		process.destroy();
	}

	/**
	 * check a source code is format or not
	 *
	 * @param fileName file need to check format
	 * @param type     type of source code
	 * @param parent   main frame
	 * @return true if source code have formatted, otherwise false
	 * @throws IOException 
	 */
	public static boolean checkFormat(String fileName, String type, frmJudge parent) throws IOException {
		Format(fileName + "." + type, type, parent);
		FileHandle fhandle = new FileHandle();
		// check file is exist
		if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) { // 2 files are different, then generate
			// new file.orig
			fhandle.copyFile(fileName + "." + type + ".orig", fileName + "." + type);
			Files.deleteIfExists(Paths.get(fileName + "." + type + ".orig"));

			String elseType = "";
			if (type.equals("c") || type.equals("cpp")) {
				elseType = "java";
			} else {
				elseType = "cpp";
			}
			Format(fileName + "." + type, elseType, parent);
			if (Files.exists(Paths.get(fileName + "." + type + ".orig"))) {
				Files.exists(Paths.get(fileName + "." + type + ".orig"));
				return false;
			} else {
				return true;
			}
		} else { // 2 files are the same
			return true;
		}

	}
}
