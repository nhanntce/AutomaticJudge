/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import judge.frmJudge;
import obj.Plagiarism;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class PlagiarismerTest {
    frmJudge parent = new frmJudge();
    public PlagiarismerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of plagiarism method, of class Plagiarismer.
     */
    @Test
    public void testPlagiarism() throws Exception {
        ArrayList<String> fileChecks = new ArrayList<>();
        ArrayList<ArrayList<String>> listChecks = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        
        fileChecks.add("source code for unit test/plagiarism/C1.c");
        fileChecks.add("source code for unit test/plagiarism/Cpp1.cpp");
        fileChecks.add("source code for unit test/plagiarism/Java1.java");
        fileChecks.add("source code for unit test/plagiarism/py1.py");
        
        list1.add("source code for unit test/plagiarism/C2.c");
        list2.add("source code for unit test/plagiarism/Cpp2.cpp");
        list3.add("source code for unit test/plagiarism/Java2.java");
        list4.add("source code for unit test/plagiarism/py2.py");
        
        listChecks.add(list1);
        listChecks.add(list2);
        listChecks.add(list3);
        listChecks.add(list4);
        
        types.add("c");
        types.add("cpp");
        types.add("java");
        types.add("py");
        
        // expected
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(41);
        expected.add(100);
        expected.add(0);
        
        for (int i = 0, len = fileChecks.size(); i < len; i++) {
            ArrayList<Plagiarism> plagiarism = Plagiarismer.plagiarism(fileChecks.get(i), listChecks.get(i),
                    types.get(i), parent);
            if(plagiarism.size() > 0)
                assertEquals(expected.get(i), plagiarism.get(0));
            else {
                assertTrue(true);
            }
        }
    }
}
