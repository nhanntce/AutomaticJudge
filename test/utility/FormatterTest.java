/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import judge.FileHandle;
import judge.frmJudge;

/**
 *
 * @author NhanNT
 */
public class FormatterTest {
    frmJudge parent = new frmJudge();
    static FileHandle fhandle = new FileHandle();
    public FormatterTest() {
    }
    
    /**
     * reset data to prepare to test	
     */
    @BeforeClass
    public static void setUpClass() {
    	fhandle.copyFile("source code for unit test/data/JavaFormatted.java", "source code for unit test/JavaFormatted.java");
    	fhandle.copyFile("source code for unit test/data/JavaUnformatted.java", "source code for unit test/JavaUnformatted.java");
    	fhandle.copyFile("source code for unit test/data/CppFormatted.cpp", "source code for unit test/CppFormatted.cpp");
    	fhandle.copyFile("source code for unit test/data/CppUnformatted.cpp", "source code for unit test/CppUnformatted.cpp");
    	fhandle.copyFile("source code for unit test/data/CFormatted.c", "source code for unit test/CFormatted.c");
    	fhandle.copyFile("source code for unit test/data/CUnformatted.c", "source code for unit test/CUnformatted.c");
    	fhandle.copyFile("source code for unit test/data/pythonTest.py", "source code for unit test/pythonTest.py");
    	
    	fhandle.copyFile("source code for unit test/data/CUnformattedCheckFormat.c", "source code for unit test/CUnformattedCheckFormat.c");
    	fhandle.copyFile("source code for unit test/data/CppUnformattedCheckFormat.cpp", "source code for unit test/CppUnformattedCheckFormat.cpp");
    	fhandle.copyFile("source code for unit test/data/JavaUnformattedCheckFormat.java", "source code for unit test/JavaUnformattedCheckFormat.java");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Format method, of class Formatter.
     * There are 7 case to cover this function
     */
    @Test
    public void testFormat() throws Exception {
        System.out.println("Format");
        ArrayList<String> pathFile = new ArrayList<>();
        pathFile.add("source code for unit test/JavaFormatted.java");
        pathFile.add("source code for unit test/JavaUnformatted.java");
        pathFile.add("source code for unit test/CppFormatted.cpp");
        pathFile.add("source code for unit test/CppUnformatted.cpp");
        pathFile.add("source code for unit test/CFormatted.c");
        pathFile.add("source code for unit test/CUnformatted.c");
        pathFile.add("source code for unit test/pythonTest.py");
        ArrayList<Boolean> expected = new  ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        expected.add(Boolean.TRUE);
        for(int i = 0, len = pathFile.size(); i < len; i++) {
            String type = pathFile.get(i).split("\\.")[1].replace("\"", "");
            Formatter.Format(pathFile.get(i), type, parent);
            boolean result = Files.exists(Paths.get(pathFile.get(i).replace("\"", "") + ".orig"));
            assertEquals(expected.get(i), (!result));
        }
    }

    /**
     * Test of checkFormat method, of class Formatter.
     * There are 7 case to cover this function
     * @throws IOException 
     */
    @Test
    public void testCheckFormat() throws IOException {
    	ArrayList<String> pathFile = new ArrayList<>();
        pathFile.add("source code for unit test/JavaFormatted");
        pathFile.add("source code for unit test/JavaUnformattedCheckFormat");
        pathFile.add("source code for unit test/CppFormatted");
        pathFile.add("source code for unit test/CppUnformattedCheckFormat");
        pathFile.add("source code for unit test/CFormatted");
        pathFile.add("source code for unit test/CUnformattedCheckFormat");
        pathFile.add("source code for unit test/pythonTest");
        ArrayList<Boolean> expected = new  ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);
        expected.add(Boolean.TRUE);
        expected.add(Boolean.TRUE);
        expected.add(Boolean.TRUE);
        ArrayList<String> extension = new ArrayList<String>();
        extension.add("java");
        extension.add("java");
        extension.add("cpp");
        extension.add("cpp");
        extension.add("c");
        extension.add("c");
        extension.add("py");
        for(int i = 0, len = pathFile.size(); i < len; i++) {
            boolean result = Formatter.checkFormat(pathFile.get(i), extension.get(i), parent);
            assertEquals(expected.get(i), result);
        }
    }
    
}
