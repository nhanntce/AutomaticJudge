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
 * @author NhanNT
 */
public class PlagiarismerTestTest {

    frmJudge parent = new frmJudge();

    public PlagiarismerTestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of plagiarism method, of class Plagiarismer.
     * This function has 4 test cases
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
        //first parameters
        fileChecks.add("source code for unit test/plagiarism/C1");
        fileChecks.add("source code for unit test/plagiarism/Cpp1");
        fileChecks.add("source code for unit test/plagiarism/Java1");
        fileChecks.add("source code for unit test/plagiarism/py1");
        //second parameter
        list1.add("source code for unit test/plagiarism/C2.c");
        list1.add("source code for unit test/plagiarism/C3.c");
        list2.add("source code for unit test/plagiarism/Cpp2.cpp");
        list2.add("source code for unit test/plagiarism/Cpp3.cpp");
        list3.add("source code for unit test/plagiarism/Java2.java");
        list3.add("source code for unit test/plagiarism/Java3.java");
        list4.add("source code for unit test/plagiarism/py2.py");
        list4.add("source code for unit test/plagiarism/py3.py");
        listChecks.add(list1);
        listChecks.add(list2);
        listChecks.add(list3);
        listChecks.add(list4);
        //third parameter
        types.add("c");
        types.add("cpp");
        types.add("java");
        types.add("py");
        // expected
        ArrayList<ArrayList<Plagiarism>> expected = new ArrayList<>();
        ArrayList<Plagiarism> exp1 = new ArrayList<>();
        ArrayList<Plagiarism> exp2 = new ArrayList<>();
        exp1.add(new Plagiarism(41, "source code for unit test/plagiarism/C2.c"));
        exp1.add(new Plagiarism("35 37", "67 69"));
        exp1.add(new Plagiarism("21 27", "53 59"));
        exp1.add(new Plagiarism("4 9", "6 11"));
        expected.add(exp1);
        exp2.add(new Plagiarism(100, "source code for unit test/plagiarism/Cpp2.cpp"));
        exp2.add(new Plagiarism("3 51", "3 51"));
        expected.add(exp2);
        //assert
        for (int i = 0, len = fileChecks.size(); i < len; i++) {
            ArrayList<Plagiarism> plagiarism = Plagiarismer.plagiarism(fileChecks.get(i), listChecks.get(i),
                    types.get(i), parent);
            if (plagiarism.size() > 0) {
                assertEquals(plagiarism.get(0).getPercentageOfPlagiarism(), expected.get(i).get(0).getPercentageOfPlagiarism());
                assertEquals(plagiarism.get(0).getNameOfFileHasPlagiarism(), expected.get(i).get(0).getNameOfFileHasPlagiarism());
                for (int j = 1, len1 = plagiarism.size(); j < len1; j++) {
                    assertEquals(plagiarism.get(j).getCompareWith(), expected.get(i).get(j).getCompareWith());
                    assertEquals(plagiarism.get(j).getOrigianal(), expected.get(i).get(j).getOrigianal());
                }
            } else {
                assertTrue(true);
            }
        }
    }
}
