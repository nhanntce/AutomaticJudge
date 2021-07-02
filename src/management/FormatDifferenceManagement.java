package management;

import java.util.ArrayList;
import java.util.List;
import obj.FormatDifference;
import utility.Tool;



/**
 * 
 * @author TuPTA
 *
 */
public class FormatDifferenceManagement {

	public List<FormatDifference> checkFormat(String path, String pathToCompare) {
		List<FormatDifference> lf = new ArrayList<FormatDifference>();
		int lineStart = 0;
		int lineEnd = 0;
		String content1 = "";
		String content2 = "";
		boolean isDifference = false;
		try {
			List<String> line1 = Tool.readFile(path);
			List<String> line2 = Tool.readFile(pathToCompare);

			int min = 0;
			if(line1.size() < line2.size()) {
				min = line1.size();
			} else {
				min = line2.size();
			}
			
			for (int i = 0; i < min; i++) {
				
				if(isDifference) {
					if(!line1.get(i).equals(line2.get(i))) {
						content1 += "\n" + line1.get(i).toString();
						content2 += "\n" + line2.get(i).toString();
						if(i + 1 == min)
							lf.add(new FormatDifference(lineStart, i + 1, content1, content2));
						continue;
					} else {
						lf.add(new FormatDifference(lineStart, i + 1, content1, content2));
						content1 = "";
						content2 = "";
						isDifference = false;
						continue;
					}
				}
				
				if (!line1.get(i).equals(line2.get(i))) {
					lineStart = i;
					isDifference = true;
					content1 = line1.get(i).toString();
					content2 = line2.get(i).toString();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return lf;
	}

}
