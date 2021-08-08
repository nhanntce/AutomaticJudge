package judge;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Vuong Khang Hy 
 * Email: khanghy3004@gmail.com 
 * Automatic Judger
 */
public class FileWatcher implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(FileWatcher.class.getName());

    private WatchService watcher;
    private FileHandler fileHandler;
    private List<Kind<?>> watchedEvents;
    private Path directoryWatched;

    /**
     * @param directory directory to watch files into
     * @param fileHandler implemented instance to handle the file event
     * @param watchRecursive if directory is to be watched recursively
     * @param watchedEvents Set of file events watched
     *
     * @throws IOException if having some errors
     */
    public FileWatcher(Path directory, FileHandler fileHandler, boolean watchRecursive,
            WatchEvent.Kind<?>... watchedEvents) throws IOException {
        super();
        this.watcher = FileSystems.getDefault().newWatchService();
        this.fileHandler = fileHandler;
        this.directoryWatched = directory;
        this.watchedEvents = Arrays.asList(watchedEvents);
        if (watchRecursive) {
            // register all subfolders
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    LOGGER.log(Level.INFO, "Registering {0} ", dir);
                    dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            directory.register(watcher, watchedEvents);
        }
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Starting FileWatcher for {0}", directoryWatched.toAbsolutePath());
        WatchKey key = null;
        while (true) {
            try {
                key = watcher.take();
                if (key != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        //directory in which file event is detected
                        Path directory = (Path) key.watchable();
                        Path fileName = ev.context();
                        if (watchedEvents.contains(kind)) {
                            LOGGER.log(Level.INFO, "Invoking handle on {0}", fileName.toAbsolutePath());
                            fileHandler.handle(directory.resolve(fileName).toFile(), kind);
                        }
                    }
                    key.reset();
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, "Polling Thread was interrupted ", ex);
                Thread.currentThread().interrupt();
            }
        }
    }
}
