package judge;

import java.io.File;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
@Ignore
public class FileHandlerTest implements FileHandler {

    private static final Logger LOGGER = Logger.getLogger(FileHandlerTest.class.getName());
    frmJudge tool;

    public FileHandlerTest(frmJudge tool) {
        this.tool = tool;

    }
    /**
     * check Type of file test
     * LinhNC
     */
    private boolean checkType(String filename) {
        String[] lstype = filename.split("\\.");
        String type = lstype[lstype.length - 1];
        return type.equalsIgnoreCase("cpp") || type.equalsIgnoreCase("c") || type.equalsIgnoreCase("py") ||  type.equalsIgnoreCase("sql");
    }
    /**
     * auto Judge
     * LinhNC
     */
    @Override
    public void handle(File file, Kind<?> fileEvent) {
        LOGGER.log(Level.INFO, "Handler is triggered for file {0}", file.getPath());
        
        if (fileEvent == StandardWatchEventKinds.ENTRY_CREATE && checkType(file.getName())) {
            System.out.println(file.getPath());
            if(tool.judge == null || !tool.judge.isAlive()) {
                tool.autoJudge();
            } else {
                synchronized (tool.judge) {
                    try {
                        tool.judge.wait();
                        tool.autoJudge();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FileHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }  
        }
    }
}
