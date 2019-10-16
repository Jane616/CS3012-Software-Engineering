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
    
    //test for add_path method
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
    
    //test for size_traverse method
    @Test
    public void testSize_traverse() {
    	Node root = new Node(1);
    	root.left = new Node(2);
    	root.right = new Node(3);
    	root.left.left = new Node(4);
    	Node tmp = new Node(5);
    	root.left.right = tmp;
    	root.right.left = new Node(6);
    	root.right.right = new Node(7);
    	root.right.left.left = tmp;
    	
    	ArrayList<Integer> result_data = new ArrayList<Integer> ();
    	result_data.add(1);
    	result_data.add(2);
    	result_data.add(3);
    	result_data.add(4);
    	result_data.add(6);
    	result_data.add(7);
    	result_data.add(5);
    	
    	LCASolution testSolution = new LCASolution();
    	//test for empty tree input
    	assertEquals("size_traverse test failed - empty tree input - return value", 0, testSolution.size_traverse(null));
    	
    	//test for non-empty tree input
    	assertEquals("size_traverse test failed - non-empty tree input - return value", 7, testSolution.size_traverse(root));
    	assertEquals("size_traverse test failed - non-empty tree input - recored data", result_data, testSolution.data);
    	
    }
    
    //test for findpath method
    @Test
    public void testFindpath() {
    	LCASolution testSolution = new LCASolution();
    	
    	//test for empty DAG input
    	assertFalse("findpath test failed - empty DAG input - return value", testSolution.findpath(null, 0, testSolution.path1));
    	
    	//test for regular DAG input
    	Node root = new Node(1);
    	root.left = new Node(2);
    	root.right = new Node(3);
    	root.left.left = new Node(4);
    	Node tmp = new Node(5);
    	root.left.right = tmp;
    	root.right.left = new Node(6);
    	root.right.right = new Node(7);
    	root.right.left.left = tmp;
    	
    	int size = testSolution.size_traverse(root);
        testSolution.path1 = new ArrayList<Integer>(Collections.nCopies(size, 0));
        
        //test for node that doesn't exist in DAG
        assertFalse("findpath test failed - non-existing node input - return value", testSolution.findpath(root, 10, testSolution.path1));
        
        //test for regular inputs
    	ArrayList<Integer> result_path = new ArrayList<Integer> ();
    	result_path.add(2);
    	result_path.add(1);
    	result_path.add(1);
    	result_path.add(0);
    	result_path.add(1);
    	result_path.add(0);
    	result_path.add(2);
    	assertTrue("findpath test failed - regular input - return value", testSolution.findpath(root, 5, testSolution.path1));
    	assertEquals("findpath test failed - regular input - recorded path", result_path,testSolution.path1);
    }
    
    //test for findlca method
    @Test
    public void testFindlca() {
    	LCASolution testSolution = new LCASolution();
    	Node root = new Node(1);
    	root.left = new Node(2);
    	root.right = new Node(3);
    	root.left.left = new Node(4);
    	root.right.right = new Node(6);
    	Node tmp = new Node(5);
    	root.left.right = tmp;
    	root.right.left = tmp;
    	
    	//test for empty DAG input
    	assertFalse("findlca test failed - empty DAG input - return value", testSolution.findlca(null, 1, 2));
    	
    	//test for invalid target input
    	assertFalse("findlca test failed - same target input - return value", testSolution.findlca(root, 1, 1));
    	assertFalse("findlca test failed - target1 non exist - return value", testSolution.findlca(root, 10, 2));
    	assertFalse("findlca test failed - target2 non exist - return value", testSolution.findlca(root, 2, 10));
    	assertFalse("findlca test failed - targets non exist - return value", testSolution.findlca(root, 20, 10));
    	
    	//test for regular input
    	//result is root node
    	assertTrue("findlca test failed - lca is root - return value", testSolution.findlca(root, 5, 6));
    	assertEquals("findlca test failed - lca is root - recorded lca", 1, testSolution.lca);
    	
    	//result is not root node
    	root.right.left = new Node(7);
    	assertTrue("findlca test failed - regular input - return value", testSolution.findlca(root, 7, 6));
    	assertEquals("findlca test failed - regular input - recorded lca", 3, testSolution.lca);
    }
    
    //test for situation where both target nodes have multiple paths from root node
    @Test
    public void testMulpath () {
    	Node root = new Node(1);
    	Node tmp = new Node(2);
    	root.left = tmp;
    	root.right = new Node(3);
    	root.right.left = tmp;
    	tmp = new Node(4);
    	root.left.left = tmp;
    	root.left.right = new Node(5);
    	root.left.right.left = tmp;
    	
    	LCASolution testSolution = new LCASolution();
    	assertTrue("testMulpath failed - input 4, 5 - return value", testSolution.findlca(root, 4, 5));
    	assertEquals("testMulpath failed - input 4, 5 - recorded lca", 2, testSolution.lca);
    	
    }

    
}
