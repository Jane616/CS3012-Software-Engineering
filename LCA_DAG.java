import java.util.ArrayList;
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
    int lca;
    
    LCASolution () {
        path1 = new ArrayList<Integer>();
        path2 = new ArrayList<Integer>();
        data = new ArrayList<Integer>();
        lca = -1;
    	
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

        path.add(root.data);
        if (root.data == target){
            return true;
        }

        if (findpath(root.left, target, path) || findpath(root.right, target, path)){
            return true;
        }

        // remove this node from recorded path if target node is not in its children
        path.remove(path.size() - 1);
        return false;
    }

    //compare 2 paths and find LCA
    boolean findlca (Node root, int target1, int target2){
        path1.clear();
        path2.clear();
        
        if (target1 == target2) {
        	return false;
        }

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
    	test.right.right.left = tmp;
    	
    	LCASolution lca = new LCASolution();
    	System.out.println(lca.size_traverse(test));
    	System.out.println(lca.data.toString());
    }
}
