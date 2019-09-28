import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

//-------------------------------------------------------------------------
/**
 *  Test class for LCA.java
 *
 */
@RunWith(JUnit4.class)
public class LCATest
{

	//test for Node class constructor
    @Test
    public void testNodeConstructor()
    {
      Node test = new Node(2);
      assertEquals("Node class constructor testing failed. - test.data", 2, test.data);
      assertNull("Node class constructor testing failed. - test.left", test.left);
      assertNull("Node class constructor testing failed. - test.right", test.right);
    }
    
    //test for LCASolution class constructor
    @Test
    public void testLCASolutionConstructor()
    {
    	LCASolution testSolution = new LCASolution();
    	assertEquals("LCASolution class constructor testing failed - testSolution.lca", -1, testSolution.lca);
    	assertNotNull("LCASolution class constructor testing failed - testSolution.path1", testSolution.path1);
    	assertNotNull("LCASolution class constructor testing failed - testSolution.path2", testSolution.path2);
    	
    }
    
    //test for findpath method with invalid input
    @Test
    public void testfindpathInvalid() {
    	LCASolution testSolution = new LCASolution();
    	assertFalse("findpathInvalid test failed", testSolution.findpath(null, 2, testSolution.path1));
    }
    
    //test for findpath method with no iteration
    @Test
    public void testfindpathNoIteration() {
    	Node testRoot = new Node(3);
    	testRoot.left = new Node(1);
    	testRoot.right = new Node(2);
    	ArrayList<Integer> testpath = new ArrayList<Integer>();
    	testpath.add(3);
    	
    	
    	LCASolution testSolution = new LCASolution();
    	assertTrue("findpathNoIteration test failed - return value", testSolution.findpath(testRoot, 3, testSolution.path1));
    	assertEquals("findpathNoIteration test failed - recorded path", testpath, testSolution.path1);
    }
    

    
}
