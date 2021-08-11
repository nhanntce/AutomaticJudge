/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import obj.Comment;
import obj.MyFunction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class FunctionManagementTest {

    public FunctionManagementTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of separateFunction method, of class FunctionManagement.
     */
    @Test
    public void testSeparateFunction() throws IOException {
        System.out.println("separateFunction");
        List<String> lines = Files.readAllLines(Paths.get("source code for unit test/seperateComment.java"));
        FunctionManagement instance = new FunctionManagement();
        List<MyFunction> result = instance.separateFunction(lines);
        assertEquals(8, result.get(0).getStartIndex());
        assertEquals(26, result.get(0).getEndIndex());
        assertEquals(32, result.get(1).getStartIndex());
        assertEquals(35, result.get(1).getEndIndex());
        assertEquals(42, result.get(2).getStartIndex());
        assertEquals(44, result.get(2).getEndIndex());
    }

    /**
     * Test of checkFunctionComment method, of class FunctionManagement.
     */
    @Test
    public void testCheckFunctionComment() throws IOException {
        System.out.println("checkFunctionComment");
        ArrayList<Comment> cmts = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("source code for unit test/seperateComment.java"));
        cmts.add(new Comment("/**\n* sort array inreasing\n* @param args\n     */", 4, 7));
        cmts.add(new Comment("//call sort /*function*/ of Arrays libary", 15, 15));
        cmts.add(new Comment("/**comment here*/", 20, 20));
        cmts.add(new Comment("//display", 22, 22));
        cmts.add(new Comment("/**commented between*/", 24, 24));
        cmts.add(new Comment("/**\n     * display data\n     * @param data \n     */", 28, 31));
        cmts.add(new Comment("//show data", 33, 33));
        cmts.add(new Comment("/**\n     * display\n     * @param data\n     * @return \n     */", 37, 41));
        cmts.add(new Comment("//return value", 43, 43));

        FunctionManagement instance = new FunctionManagement();
        instance.separateFunction(lines);
        instance.checkFunctionComment(cmts);
        List<MyFunction> checkList = instance.getList();
        
        assertEquals(true, checkList.get(0).isCommented());
        assertEquals(true, checkList.get(1).isCommented());
        assertEquals(true, checkList.get(2).isCommented());
    }

    /**
     * Test of checkAlgorithmComment method, of class FunctionManagement.
     */
    @Test
    public void testCheckAlgorithmComment() throws IOException {
        System.out.println("checkAlgorithmComment");
        List<String> lines = Files.readAllLines(Paths.get("source code for unit test/seperateComment.java"));
        
        ArrayList<Comment> cmts = new ArrayList<>();
        cmts.add(new Comment("/**\n* sort array inreasing\n* @param args\n     */", 4, 7));
        cmts.add(new Comment("//call sort /*function*/ of Arrays libary", 15, 15));
        cmts.add(new Comment("/**comment here*/", 20, 20));
        cmts.add(new Comment("//display", 22, 22));
        cmts.add(new Comment("/**commented between*/", 24, 24));
        cmts.add(new Comment("/**\n     * display data\n     * @param data \n     */", 28, 31));
        cmts.add(new Comment("//show data", 33, 33));
        cmts.add(new Comment("/**\n     * display\n     * @param data\n     * @return \n     */", 37, 41));
        cmts.add(new Comment("//return value", 43, 43));
        cmts.add(new Comment("//return number", 45, 45));
        cmts.add(new Comment("//number", 47, 47));
        
        FunctionManagement instance = new FunctionManagement();
        instance.separateFunction(lines);
        instance.checkAlgorithmComment(cmts);
        List<MyFunction> checkList = instance.getList();
        for (MyFunction myFunction : checkList) {
            System.out.println(myFunction.getContent());
            System.out.println(myFunction.isIsAlgorithmCommented());
        }
        assertEquals(true, checkList.get(0).isIsAlgorithmCommented());
        assertEquals(true, checkList.get(1).isIsAlgorithmCommented());
        assertEquals(true, checkList.get(2).isIsAlgorithmCommented());
        assertEquals(false, checkList.get(3).isIsAlgorithmCommented());
    }
    /**
     * Test of calculatePercentOfAllFunctionCmt method, of class
     * FunctionManagement.
     */
    @Test
    public void testCalculatePercentOfAllFunctionCmt() {
        System.out.println("calculatePercentOfAllFunctionCmt");
        String path_1 = "source code for unit test/seperateComment.java";
        String path_2 = "source code for unit test/testcalculatepercentcmt.java";

        FunctionManagement instance = new FunctionManagement();
        double expResult_1 = 75.0;
        double expResult_2 = 33.33333333333333;
        double result_1 = instance.calculatePercentOfAllFunctionCmt(path_1);
        double result_2 = instance.calculatePercentOfAllFunctionCmt(path_2);
        assertEquals(expResult_1, result_1, 0.0);
        assertEquals(expResult_2, result_2, 0.0);
    }
}
