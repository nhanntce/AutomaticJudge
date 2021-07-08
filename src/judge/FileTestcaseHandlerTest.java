/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package judge;

import java.io.File;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.logging.Logger;

/**
 *
 * @author NhanNT and ThaoNM
 */
public class FileTestcaseHandlerTest implements FileHandler {

    private static final Logger LOGGER = Logger.getLogger(FileHandlerTest.class.getName());
    frmJudge tool;

    public FileTestcaseHandlerTest(frmJudge tool) {
        this.tool = tool;

    }

    /**
     * auto Judge LinhNC
     */
    @Override
    public void handle(File file, WatchEvent.Kind<?> fileEvent) {
        System.out.println("Loi ");
        if ((fileEvent == StandardWatchEventKinds.ENTRY_CREATE
                || fileEvent == StandardWatchEventKinds.ENTRY_DELETE
                || fileEvent == StandardWatchEventKinds.ENTRY_MODIFY)) {

            tool.listProblemA();
            tool.loadStudentToTable();
            tool.loadAllPoint();

        }
    }

}