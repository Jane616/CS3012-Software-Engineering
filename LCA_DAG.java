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
        	System.out.println(curpath.toString());
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
        	/*//handling multiple routes
        	int route_left = 0;
        	int route_right = 0;
        	
        	if (root.left != null) {
        		int index_left = data.indexOf(root.left.data);
        		route_left = path.get(index_left);
        	}
        	
        	if (root.right != null) {
        		int index_right = data.indexOf(root.right.data);
        		route_right = path.get(index_right);
        	}
        	
        	path.set(index, (route_left + route_right));
        	*/
            return true;
        }

        // remove this node from recorded path if target node is not in its children
        curpath.remove(curpath.indexOf(root.data));
        return false;
    }

    //compare 2 paths and find LCA
    boolean findlca (Node root, int target1, int target2){
        path1.clear();
        path2.clear();
        
        if (target1 == target2) {
        	return false;
        }
        
        int len = size_traverse(root);
        //empty tree
        if (len == 0) {
        	return false;
        }
        
        //initialize path
        //path record number of possible routes that 
        //pass through this corresponding node in ArrayList data
        path1 = new ArrayList<Integer>(Collections.nCopies(len, 0));
        path2 = new ArrayList<Integer>(Collections.nCopies(len, 0));
        
        boolean find1 = findpath(root, target1, path1);
        boolean find2 = findpath(root, target2, path2);

        if (find1 == false || find2 == false){
            return false;
        }
        
        int size1 = path1.size();
        int size2 = path2.size();

        int i = 0;
        while (i < size1 && i < size2){
            if (path1.get(i) != path2.get(i)){
                break;
            }

            i++;
        }

        lca = path1.get(i - 1);
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
    	System.out.println(lca.size_traverse(test));
    	System.out.println(lca.data.toString());
    	
    	lca.path1 = new ArrayList<Integer>(Collections.nCopies(6, 0));
    	System.out.println(lca.findpath(test, 6, lca.path1));
    	lca.curpath.clear();
    	System.out.println(lca.path1.toString());
    	
    	lca.path1 = new ArrayList<Integer>(Collections.nCopies(6, 0));
    	System.out.println(lca.findpath(test, 4, lca.path1));
    	lca.curpath.clear();
    	System.out.println(lca.path1.toString());
    }
}
