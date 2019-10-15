import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

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
