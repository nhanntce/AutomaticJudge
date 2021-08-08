package judge;

import java.io.File;
import java.nio.file.WatchEvent;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
public interface FileHandler {

    /**
     * Method is invoked post file event is detected
     *
     * @param file folder change
     * @param fileEvent java object
     * @throws InterruptedException if having some errors
     */
    void handle(File file, WatchEvent.Kind<?> fileEvent) throws InterruptedException;
}
