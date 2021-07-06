package management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author TuPTA
 *
 */
public class CompareFiles {

    /**
     * compare two files are the same or not the same if two files are the same
     * this function will return true, else it will return false
     *
     * @param path the path of first file
     * @param pathToCompare the path second file
     * @return True if two files are the same, False if 2 files are not the same
     */
    public boolean CompareFiles(Path path, Path pathToCompare) {
        try {
            //
            BufferedReader br1 = null;
            BufferedReader br2 = null;
            String sCurrentLine;
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();

            br1 = new BufferedReader(new FileReader(path.toString()));
            br2 = new BufferedReader(new FileReader(pathToCompare.toString()));
            // if exist line in file, then add to list1 variable
            while ((sCurrentLine = br1.readLine()) != null) {
                list1.add(sCurrentLine);

            }
            // if exist line in file, then add to list2 variable
            while ((sCurrentLine = br2.readLine()) != null) {
                list2.add(sCurrentLine);

            }
            br1.close();
            br2.close();
            // create tmp list
            List<String> tmpList = new ArrayList<String>(list1);
            // remove all list 2 from tmpList
            tmpList.removeAll(list2);
            // after remove 
            if (tmpList.size() == 0) { //if size = 0 means two files are the same
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
        //if size != 0 means two files are not the same
        return false;
    }

}
