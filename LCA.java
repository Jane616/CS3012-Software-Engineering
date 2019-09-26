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

    public static void main(String[] args) {
        Node tree = new Node(3);
        tree.left = new Node(2);
        tree.left.right = new Node(1);
        tree.right = new Node(4);
        tree.right.left = new Node(5);

        LCASolution solution = new LCASolution();
        solution.path1 = new ArrayList<Integer>();
        solution.path2 = new ArrayList<Integer>();
        System.out.println(solution.findpath(tree, 2, solution.path1));
        System.out.println(solution.findpath(tree, 5, solution.path1));
        System.out.println(solution.findpath(tree, 10, solution.path1));
    }

}