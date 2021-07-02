package management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 
 * @author TuPTA
 *
 */
public class CompareFiles {

	BufferedReader br1 = null;
	BufferedReader br2 = null;

	/**
	 * compare two files are the same or not the same
	 * if two files are the same this function will return true, else it will return false
	 * @param path
	 * @param pathToCompare
	 */
	public boolean CompareFiles(String path, String pathToCompare) {
		// Path comparison
		if (path.compareTo(pathToCompare) == 0) {
			// file1 and file2 are same
			return true;
		} else {
			// file1 and file2 are not same
			return false;
		}

	}

}
