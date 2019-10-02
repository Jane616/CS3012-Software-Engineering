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
    
    //test for findpath method with a depth 3 binary tree
    @Test
    public void testfindpathDepth3Binary() {
    	Node testRoot = new Node(1);
    	testRoot.left = new Node(2);
    	testRoot.right = new Node(3);
    	testRoot.left.left = new Node(4);
    	testRoot.left.right = new Node(5);
    	testRoot.right.left = new Node(6);
    	testRoot.right.right = new Node(7);
    	
    	ArrayList<Integer> testpath2 = new ArrayList<Integer>();
    	testpath2.add(1);
    	testpath2.add(2);
    	
    	ArrayList<Integer> testpath3 = new ArrayList<Integer>();
    	testpath3.add(1);
    	testpath3.add(3);
    	
    	ArrayList<Integer> testpath5 = new ArrayList<Integer>();
    	testpath5.add(1);
    	testpath5.add(2);
    	testpath5.add(5);
    	
    	ArrayList<Integer> testpath6 = new ArrayList<Integer>();
    	testpath6.add(1);
    	testpath6.add(3);
    	testpath6.add(6);
    	
    	ArrayList<Integer> testpath8 = new ArrayList<Integer>();
    	
    	LCASolution testSolution = new LCASolution();
    	assertTrue("findpathDepth3Binary test failed - return value - 2", testSolution.findpath(testRoot, 2, testSolution.path1));
    	assertEquals("findpathDepth3Binary test failed - recorded path - 2", testpath2, testSolution.path1);
    	
    	testSolution.path1.clear();
    	assertTrue("findpathDepth3Binary test failed - return value - 3", testSolution.findpath(testRoot, 3, testSolution.path1));
    	assertEquals("findpathDepth3Binary test failed - recorded path - 3", testpath3, testSolution.path1);
    	
    	testSolution.path1.clear();
    	assertTrue("findpathDepth3Binary test failed - return value - 5", testSolution.findpath(testRoot, 5, testSolution.path1));
    	assertEquals("findpathDepth3Binary test failed - recorded path - 5", testpath5, testSolution.path1);
    	
    	testSolution.path1.clear();
    	assertTrue("findpathDepth3Binary test failed - return value - 6", testSolution.findpath(testRoot, 6, testSolution.path1));
    	assertEquals("findpathDepth3Binary test failed - recorded path - 6", testpath6, testSolution.path1);
    	
    	testSolution.path1.clear();
    	assertFalse("findpathDepth3Binary test failed - return value - 8", testSolution.findpath(testRoot, 8, testSolution.path1));
    	assertEquals("findpathDepth3Binary test failed - recorded path - 8", testpath8, testSolution.path1);
    }
    
    //test for findlca method with invalid tree input
    @Test
    public void testfindlcaInvalidTree() {
    	LCASolution testSolution = new LCASolution();
    	assertFalse("findlcaInvalidTree test failed - return value", testSolution.findlca(null, 1, 2));
    	assertEquals("findlcaInvalidTree test failed - recorded lca", -1, testSolution.lca);

    }
    
    //test for findlca method with invalid target inputs
    @Test
    public void testfindlcaInvalidTarget() {
    	Node Root = new Node(1);
    	Root.left = new Node(2);
    	Root.right = new Node(3);
    	
    	LCASolution testSolution = new LCASolution();
    	//target 1 is invalid
    	assertFalse("findlcaInvalidTarget test failed - return value - 0, 1", testSolution.findlca(Root, 0, 1));
    	//target 2 is invalid
    	assertFalse("findlcaInvalidTarget test failed - return value - 1, 0", testSolution.findlca(Root, 1, 0));
    	//both targets are invalid
    	assertFalse("findlcaInvalidTarget test failed - return value - 0, 0", testSolution.findlca(Root, 0, 0));
    	// both targets are in the tree but they are the same node
    	assertFalse("findlcaInvalidTarget test failed - return value - 1, 1", testSolution.findlca(Root, 1, 1));
    
    }
	
    //test for findlca method with a depth 3 binary tree
    @Test
    public void testfindlcaDepth3Binary ( ) {
    	Node Root = new Node (1);
    	Root.left = new Node (2);
    	Root.right = new Node (3);
    	Root.left.left = new Node (4);
    	Root.left.right = new Node (5);
    	Root.right.left = new Node (6);
    	Root.right.right = new Node (7);
    	
    	LCASolution testSolution = new LCASolution ();
    	//when the root node is LCA
    	assertTrue("findlcaDepth3Binary test failed - return value - 4, 7", testSolution.findlca(Root, 4, 7));
    	assertEquals("findlcaDepth3Binary test failed - recorded lca - 4, 7",1, testSolution.lca);
    	//when one of the target nodes is the ancestor of the other
    	assertTrue("findlcaDepth3Binary test failed - return value - 2, 5", testSolution.findlca(Root, 2, 5));
    	assertEquals("findlcaDepth3Binary test failed - recorded lca - 2, 5",2, testSolution.lca);
    	//common situation
    	assertTrue("findlcaDepth3Binary test failed - return value - 4, 5", testSolution.findlca(Root, 4, 5));
    	assertEquals("findlcaDepth3Binary test failed - recorded lca - 4, 5",2, testSolution.lca);
    }

    //test for findlca method with a tree whose nodes may have multiple parents
    @Test
    public void testfindlcaMulParent() {
    	Node Root = new Node (1);
    	Root.left = new Node (2);
    	Root.right = new Node (3);
    	Node tmp = new Node (5);
    	Root.left.left = new Node (4);
    	Root.left.right = tmp;
    	Root.right.left = tmp;
    	Root.right.right = new Node (6);
    	
    	LCASolution testSolution = new LCASolution();
    	//find lca by left parent's path
    	assertTrue("findlcaMulParent test failed - return value - 4, 5", testSolution.findlca(Root, 4, 5));
    	assertEquals("findlcaMulParent test failed - recorded lca - 4, 5",2, testSolution.lca);
    	//find lca by right parent's path
    	//test failed because only recorded path through left parent
    	assertTrue("findlcaMulParent test failed - return value - 6, 5", testSolution.findlca(Root, 6, 5));
    	assertEquals("findlcaMulParent test failed - recorded lca - 6, 5",3, testSolution.lca);
    }
    
}
