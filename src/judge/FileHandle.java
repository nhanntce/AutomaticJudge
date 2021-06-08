package judge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
public class FileHandle {

    public void copyFile(String source, String dest) {
        try {
            FileUtils.copyFile(new File(source), new File(dest), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean compareTwoFiles(String file1Path, String file2Path) throws IOException {

        File file1 = new File(file1Path);
        File file2 = new File(file2Path);
        if (file1.exists() && file2.exists()) {
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));

            String thisLine;
            String thatLine;

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            while ((thisLine = br1.readLine()) != null) {
                list1.add(thisLine);
            }
            while ((thatLine = br2.readLine()) != null) {
                list2.add(thatLine);
            }

            br1.close();
            br2.close();

            return list1.equals(list2);
        }
        return false;
    }
}
