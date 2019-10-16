import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

//-------------------------------------------------------------------------
/**
 *  Test class for LCA_DAG.java
 * 	special test cases for DAG
 *
 */
@RunWith(JUnit4.class)
public class LCA_DAGTest
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
    	assertNotNull("LCASolution class constructor testing failed - testSolution.data", testSolution.data);
    	assertNotNull("LCASolution class constructor testing failed - testSolution.curpath", testSolution.curpath);
    	
    }
    
    //test for findmax method
    @Test
    public void testFindmax() {
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	path.add(1);
    	path.add(10);
    	path.add(5);
    	LCASolution testSolution = new LCASolution();
    	
    	assertEquals("findmax test failed - return value", 10, testSolution.findmax(path) );
    }
    
    @Test
    public void testAdd_path() {
    	LCASolution testSolution = new LCASolution();
    	testSolution.path1 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    	testSolution.path2 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    	testSolution.data.add(1);
    	testSolution.data.add(3);
    	testSolution.data.add(2);
    	testSolution.data.add(4);
    	testSolution.curpath.add(1);
    	testSolution.curpath.add(3);
    	testSolution.curpath.add(4);
    	
    	ArrayList<Integer> result_path = new ArrayList<Integer> ();
    	result_path.add(1);
    	result_path.add(1);
    	result_path.add(0);
    	result_path.add(1);
    	
    	testSolution.add_path(testSolution.path1);
    	assertEquals("add_path test failed - recorded path", result_path, testSolution.path1);
    	
    }

    
}
