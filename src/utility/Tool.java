package utility;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author NhanNT
 *
 */
public class Tool {
	/**
	 * get the first index of character expect space and comment characters like //
	 * /* /**
	 * 
	 * @param s
	 * @return
	 */
	public static int getindexOfCodeLine(String s) {
		Pattern pattern = Pattern.compile("\\w|}|\\{|\\)|\\(|\\[|\\]|\\?|\\+|-|:|-|%|\\^|~|\"\"|\\$|,|=|>|<");
		Matcher matcher = pattern.matcher(s);
		return matcher.find() ? matcher.start() : s.length();
	}

	/**
	 * check whether a line is blank or not
	 * 
	 * @param s
	 * @return
	 */
	public static Boolean checkBlankLine(String s) {
		s = s.replaceAll("\\s+", "");
		return s.isEmpty();
	}

	public static void removeBlankLine(List<String> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (checkBlankLine(lines.get(i)))
				lines.remove(i);
		}
	}

	/**
	 * Count number line in content of function
	 * @param str
	 * @return
	 */
	public static int countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}
	
	/**
	 * Check a file is C or C++ source and read it line by line
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<String> readFile(String path) throws Exception{
		if(!path.endsWith(".cpp") && !path.endsWith(".c"))
			throw new Exception("The file must be C or C++ source code!");
		List<String> lines = new ArrayList<String>();
		File file = new File(path);
		if(file.exists()) {
			lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
			return lines;
		} else {
			throw new Exception("The file is not exists");
		}
	}
        public static String replaceLast(String text, String regex, String replacement) {
            return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
        }
}
