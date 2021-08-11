/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import judge.frmJudge;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class FormatterTest {
    
    public FormatterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Format method, of class Formatter.
     */
    @Test
    public void testFormat() throws Exception {
        System.out.println("Format");
        String path = "";
        String type = "";
        frmJudge parent = null;
        List<String> lists = Files.readAllLines(Paths.get("source code for unit test/Test.java"));
        System.out.println(lists.get(0));
//        Formatter.Format(path, type, parent);
        
        assertEquals("/*", lists.get(0));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkFormat method, of class Formatter.
     */
    @Test
    public void testCheckFormat() {
        System.out.println("checkFormat");
        String fileName = "";
        String type = "";
        frmJudge parent = null;
        boolean expResult = false;
        boolean result = Formatter.checkFormat(fileName, type, parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
