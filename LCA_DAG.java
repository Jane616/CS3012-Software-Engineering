import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

class Node{
    int data;
    Node left;
    Node right;

    Node (int val){
        data = val;
        left = null;
        right = null;
    }
}

class LCASolution{
    ArrayList<Integer> path1;
    ArrayList<Integer> path2;
    ArrayList<Integer> data;	//record nodes data in DAG 
    ArrayList<Integer> curpath;	//record the current path being examined
    int lca;
    
    LCASolution () {
        path1 = new ArrayList<Integer>();
        path2 = new ArrayList<Integer>();
        data = new ArrayList<Integer>();
        curpath = new ArrayList<Integer>();
        lca = -1;
    	
    }
    
    //add records of successful path
    void add_path(ArrayList<Integer> path) {
    	for (int i = 0; i < curpath.size(); i++) {
    		int nodeData = curpath.get(i);
    		int index = data.indexOf(nodeData);
    		path.set(index, (path.get(index) + 1));
    	}
    }
    
    // find max value in an ArrayList
    int findmax (ArrayList<Integer> path) {
    	int max = path.get(0);
    	int size = path.size();
    	for (int i = 1; i < size; i++) {
    		if (path.get(i) > max) {
    			max = path.get(i);
    		}
    	}
    	return max;
    }
    
    
    int size_traverse(Node root) {
    	
    	//handling empty graph
    	if (root == null) {
    	return 0;
    	}
    	
    	//perform BFS traverse
    	Queue<Node> q = new LinkedList<Node>();
    	q.add(root);
    	while (q.isEmpty() == false) {
    		Node cur = q.remove();
    		
    		if (data.contains(cur.data)) //update data according to depth
    		{
    			data.remove(cur.data);
    		}
    		data.add(cur.data);
    		
    		if (cur.left != null) {
    			q.add(cur.left);
    		}
    		
    		if (cur.right != null) {
    			q.add(cur.right);
    		}
    	}
    	
    	return data.size();
    	

    }

    //find path from root node to target node
    boolean findpath (Node root, int target, ArrayList<Integer> path){

    	//handling empty DAG
        if (root == null){
            return false;
        }

        curpath.add(root.data);
        
        if (root.data == target){
        	add_path(path);
            return true;
        }

        boolean left = findpath(root.left, target, path);

        
        if (left) {
        	curpath.remove(curpath.indexOf(root.left.data));
        }
        
        boolean right = findpath(root.right, target, path);
        
        if (right) {
        	curpath.remove(curpath.indexOf(root.right.data));
        }
        
        if (left || right){
            return true;
        }

        // remove this node from recorded path if target node is not in its children
        curpath.remove(curpath.indexOf(root.data));
        return false;
    }

    //compare 2 paths and find LCA
    boolean findlca (Node root, int target1, int target2){
        data = new ArrayList<Integer>();
        path1.clear();
        path2.clear();
        lca = -1;
        
        //handling invalid target input
        if (target1 == target2) {
        	return false;
        }
        
    	int size = size_traverse(root);
    	
    	//handling empty tree input
    	if (size == 0) {
    		return false;
    	}
    	

        //initialize path
        //path record number of possible routes that 
        //pass through this corresponding node in ArrayList data
        path1 = new ArrayList<Integer>(Collections.nCopies(size, 0));
        path2 = new ArrayList<Integer>(Collections.nCopies(size, 0));
        
        curpath = new ArrayList<Integer>();
        boolean find1 = findpath(root, target1, path1);
        curpath = new ArrayList<Integer>();
        boolean find2 = findpath(root, target2, path2);

        //if target node not in tree
        if (find1 == false || find2 == false){
            return false;
        }
        
        //find lca
        int max1 = findmax(path1);
        int max2 = findmax(path2);
        
        for (int i = 0; i < size; i++) {
        	int numpath1 = path1.get(i);
        	int numpath2 = path2.get(i);
        	
        	if (numpath1 == max1) {
        		if (numpath2 == max2) {
        			lca = data.get(i);
        			break;
        		}
        	}
        }
    
        return true;
    }
    

    public static void main (String[] args) {
    	Node test = new Node(1);
    	test.left = new Node(2);
    	test.right = new Node(3);
    	test.left.left = new Node(6);
    	Node tmp = new Node(4);
    	test.right.left = tmp;
    	test.right.right = new Node(5);
    	test.left.right = tmp;
    	test.right.right.left = tmp;
    	
    	LCASolution lca = new LCASolution();
    	lca.findlca(test, 4, 5);
    	System.out.println(lca.lca);
    	lca.findlca(test, 1, 5);
    	System.out.println(lca.lca);
    	lca.findlca(test, 1, 2);
    	System.out.println(lca.lca);

    	
    }
}
