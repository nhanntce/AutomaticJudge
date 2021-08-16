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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class CommentManagementTest {
    
    public CommentManagementTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of separateComment method, of class CommentManagement.
     */
    @Test
    public void testSeparateComment() throws IOException {
        System.out.println("separateComment");
        List<String> lines = Files.readAllLines(Paths.get("source code for unit test/seperateComment.java"));
        CommentManagement instance = new CommentManagement();
        ArrayList<Comment> expResult = new ArrayList<>();
        
        expResult.add(new Comment("/**\n* sort array inreasing\n* @param args\n     */", 4, 7));
        expResult.add(new Comment("//call sort /*function*/ of Arrays libary", 15, 15));
        expResult.add(new Comment("/**comment here*/", 20, 20));
        expResult.add(new Comment("//display", 22, 22));
        expResult.add(new Comment("/**commented between*/", 24, 24));
        
        List<Comment> result = instance.separateComments(lines);
        
        assertEquals(expResult.get(0).getStartLine(), result.get(0).getStartLine());
        assertEquals(expResult.get(0).getEndLine(), result.get(0).getEndLine());
        
        assertEquals(expResult.get(1).getStartLine(), result.get(1).getStartLine());
        assertEquals(expResult.get(1).getEndLine(), result.get(1).getEndLine());
        
        assertEquals(expResult.get(2).getStartLine(), result.get(2).getStartLine());
        assertEquals(expResult.get(2).getEndLine(), result.get(2).getEndLine());
        
        assertEquals(expResult.get(3).getStartLine(), result.get(3).getStartLine());
        assertEquals(expResult.get(3).getEndLine(), result.get(3).getEndLine());
        
        assertEquals(expResult.get(4).getStartLine(), result.get(4).getStartLine());
        assertEquals(expResult.get(4).getEndLine(), result.get(4).getEndLine());
        
        assertEquals(1, result.get(0).getTypeOfComment());
        assertEquals(3, result.get(1).getTypeOfComment());
        assertEquals(1, result.get(2).getTypeOfComment());
        assertEquals(3, result.get(3).getTypeOfComment());
        assertEquals(1, result.get(4).getTypeOfComment());
        
        assertEquals(4, result.get(0).getNumberOfLines());
        assertEquals(1, result.get(1).getNumberOfLines());
        assertEquals(1, result.get(2).getNumberOfLines());
        assertEquals(1, result.get(3).getNumberOfLines());
        assertEquals(1, result.get(4).getNumberOfLines());
    }
}
