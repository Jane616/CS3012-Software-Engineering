import java.util.ArrayList;

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
    int lca;

    //find path from root node to target node
    boolean findpath (Node root, int target, ArrayList<Integer> path){

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
    

    //for testing purposes
    /*
    public static void main(String[] args) {
        Node tree = new Node(3);
        tree.left = new Node(2);
        tree.left.right = new Node(1);
        tree.right = new Node(4);
        tree.right.left = new Node(5);

        LCASolution solution = new LCASolution();
        solution.path1 = new ArrayList<Integer>();
        solution.path2 = new ArrayList<Integer>();

        System.out.println(solution.findlca(tree, 2, 5) + " " + solution.lca);
        System.out.println(solution.findlca(tree, 2, 10) + " " + solution.lca);
        System.out.println(solution.findlca(tree, 2, 1) + " " + solution.lca);
    }
    */

}
