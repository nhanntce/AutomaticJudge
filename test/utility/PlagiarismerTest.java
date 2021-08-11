package utility;

import java.io.IOException;
import java.util.ArrayList;
import judge.frmJudge;
import obj.Plagiarism;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlagiarismerTest {

    frmJudge parent = new frmJudge();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     *
     */
    @Test
    public void testPlagiarism() throws IOException {
        ArrayList<String> fileChecks = new ArrayList<>();
        ArrayList<ArrayList<String>> listChecks = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        fileChecks.add("source code for unit test/plagiarism/C1.c");
        fileChecks.add("source code for unit test/plagiarism/Cpp1.cpp");
        fileChecks.add("source code for unit test/plagiarism/Java1.java");
        list1.add("source code for unit test/plagiarism/C2.c");
        list2.add("source code for unit test/plagiarism/Cpp2.cpp");
        list3.add("source code for unit test/plagiarism/Java2.java");
        listChecks.add(list1);
        listChecks.add(list2);
        listChecks.add(list3);
        types.add("c");
        types.add("cpp");
        types.add("java");

        for (int i = 0, len = fileChecks.size(); i < len; i++) {
            ArrayList<Plagiarism> plagiarism = Plagiarismer.plagiarism(fileChecks.get(i), listChecks.get(i), types.get(i), parent);
        }

    }

}
