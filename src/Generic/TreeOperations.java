package Generic;

import java.util.*;

public class TreeOperations {
    int depth=0;
    List<Integer> result = new ArrayList<>();
    int maxSum = Integer.MIN_VALUE;
    List<TreeNode> visited = new ArrayList<TreeNode>();
    Queue queue = new LinkedList<TreeNode>();
    int maxWidth = 0;

    public boolean isBalanced(TreeNode root) {
        boolean isBalanced = false;

        if (root == null) return true;

        int rh = height(root.right);
        int lh = height(root.left);

        if (((rh - lh) == 1 || (lh - rh == 1) || (rh == lh)) && (isBalanced(root.left)) && (isBalanced(root.right)))
            return true;

        return isBalanced;

    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int rh = 0;
        int lh = 0;

        if (node.right != null) rh = height(node.right);
        if (node.left != null) lh = height(node.left);

        return Math.max(rh, lh) + 1;


    }

    /**
     * Many algorithms like Prim’s Minimum Spanning Tree and Dijkstra’s Single Source Shortest Path use structure similar to Breadth First Search.
     */
    public List<Integer> BFS(TreeNode root)   // Iterative .. State the runtime complexity of algorithms
    {
        if (root == null) return null;

        List<Integer> result = new ArrayList<>();
        List<TreeNode> visited = new ArrayList<TreeNode>();
        Queue queue = new LinkedList<TreeNode>();


        queue.add(root);
        result.add(root.val);
        visited.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            //right child
            if (node.left != null && !visited.contains(node.left)) {
                visited.add(node.left);
                result.add(node.left.val);
                queue.add(node.left);
            }
            //right child
            if (node.right != null && !visited.contains(node.right)) {
                visited.add(node.right);
                result.add(node.right.val);
                queue.add(node.right);
            }
        }

        return result;
    }

    public List<Integer> DFSPreorder(TreeNode root) // Recursive  O(n)
    {
        if (root != null) {
            result.add(root.val);
        }

        if (root.left != null) DFSPreorder(root.left);
        if (root.right != null) DFSPreorder(root.right);

        return result;
    }

    public List<Integer> DFSPostOrder(TreeNode root) // Recursive  O(n)
    {
        if (root.left != null) DFSPostOrder(root.left);
        if (root.right != null) DFSPostOrder(root.right);
        if (root != null) {
            result.add(root.val);
        }
        return result;
    }

    public List<Integer> DFSInorder(TreeNode root) // Recursive
    {
        if (root.left != null) DFSInorder(root.left);

        if (root != null) {
            result.add(root.val);
        }

        if (root.right != null) DFSInorder(root.right);

        return result;
    }

    // For each node, calculate longest left length, calculate longest right length and add them plus one,
    public int DFSInorder_Diameter(TreeNode root) {  // Recursive
        if (root == null) return 0;

        int maxLeft = 0;
        int rightMax = 0;

        if (root.left != null) {
            maxLeft = DFSInorder_Diameter(root.left) + 1;
        }

        if (root.right != null) {
            rightMax = DFSInorder_Diameter(root.right) +1 ;
        }

        if (maxWidth < maxLeft + rightMax) maxWidth = maxLeft + rightMax;
        return  Math.max(maxLeft, rightMax) ;
    }
    public int kthsmallest_BST2Stack(TreeNode root, int k) { // O(Height + K) , Inspired by DFS InOrder but stop after finding kth element

        //O(n) solution is BST to sorted array using DFS InOrder.

        // Analyse DFS InOrder, think of the solution, think of the data structure(Stack...)
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0)
                return root.val; // kth Inner loop execution will end up with [root var = the kth smallest element]
            root = root.right;
        }
    }

    public int kthlargestBST(TreeNode root, int k) { // n-k+1 smallest ..slower than O(n)
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.right;
            }
            root = stack.pop();
            if (--k == 0)
                return root.val; // kth Inner loop execution will end up with [root var = the kth smallest element]
            root = root.left;
        }
    }

    /**
     * Calculate the maximum sum path . A path contains one or more nodes and is a continuous.
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        List<Integer> list = DFSInorder(root);
        int [] input = new int[list.size()];
        for (int i=0; i<list.size();i++) {
            input[i] = list.get(i);
        }
        return Max_SubArray_DivideandConquer.maxSumSubarray(input,0,input.length-1);
    }

    // This function returns overall maximum path sum in 'res'
    // And returns max path sum going through root.
    int findMaxUtil(TreeNode node, Res res)
    {

        // Base Case
        if (node == null)
            return 0;

        // l and r store maximum path sum going through left and
        // right child of root respectively
        int l = findMaxUtil(node.left, res);
        int r = findMaxUtil(node.right, res);

        // Max path for parent call of root. This path must
        // include at-most one child of root
        int max_single = Math.max(Math.max(l, r) + node.val,
                node.val);


        // Max Top represents the sum when the Node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int max_top = Math.max(max_single, l + r + node.val);

        // Store the Maximum Result.
        res.val = Math.max(res.val, max_top);

        return max_single;
    }
    // Returns maximum path sum in tree with given root
    int findMaxSum(TreeNode node) {
        // Initialize result
        // int res2 = Integer.MIN_VALUE;
        Res res = new Res();
        res.val = Integer.MIN_VALUE;

        // Compute and return result
        findMaxUtil(node, res);
        return res.val;
    }

    /**
     * node values are unique
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        int depthX = findDepth_DFS( root, x);
        int depthY = findDepth_DFS( root, y);
        System.out.println("depthX = " + depthX + " depthY = " + depthY);
        if (depthX ==depthY && getParent(root, x) != getParent(root, y)) return true;
        else return false;
    }
    private int  findDepth_DFS(TreeNode root, int x){
        if (root != null) {
            if (root.val == x) return 1;
        }
        if (root.left != null) {
            int depth = findDepth_DFS(root.left,x);
            if (depth != -1) return 1 + depth ;

        }
        if (root.right != null) {
            int depth = findDepth_DFS(root.right, x);
            if (depth != -1) return 1 + depth ;
            //else return -1;
        }
        return -1;
    }
    private int  getParent(TreeNode root, int x){
        if (root != null) {
            if (root.val == x) return 1;
        }
        if (root.left != null) {
            int depth = getParent(root.left,x);
            if (depth == 1) return root.val ;
            else if (depth != -1) return depth;
        }
        if (root.right != null) {
            int depth = getParent(root.right, x);
            if (depth == 1) return root.val ;
            else if (depth != -1) return depth;
        }
        return -1;
    }

    /**
     * Binary Trie
     */
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || root.val != arr[0]) return false;
        List<TreeNode> visited = new ArrayList<TreeNode>();
        Queue<AbstractMap.SimpleEntry<TreeNode, Integer>>  queue = new LinkedList<AbstractMap.SimpleEntry<TreeNode, Integer>>();

        queue.add(new AbstractMap.SimpleEntry<TreeNode, Integer>(root, 0));
        visited.add(root);

        while (!queue.isEmpty()) {
            AbstractMap.SimpleEntry<TreeNode, Integer>  nodePair = queue.poll();
            TreeNode node  =  nodePair.getKey();
            int currIndex = nodePair.getValue();
            int indexToSearch = currIndex + 1;
            //Returns true if it is a leaf node
            if(currIndex == arr.length-1 && node.val == arr[arr.length-1] && node.left == null && node.right == null) return true;
            else if(currIndex == arr.length-1) continue;
            //left child
            if (node.left != null && !visited.contains(node.left)) {
                visited.add(node.left);
                if (arr[indexToSearch] == node.left.val) queue.add(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.left, indexToSearch));
            }
            //right child
            if (node.right != null && !visited.contains(node.right)) {
                visited.add(node.right);
                if (arr[indexToSearch] == node.right.val) queue.add(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.right, indexToSearch));
            }
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        //Use DFS since we have to traverse from root to leaves for all the possible paths. Break if the found.
        return findPathSum_DFS( root, 0 ,  sum);
    }
    public List<List<Integer>> findAllPath_WithSum(TreeNode root, int sum) {
        //Use DFS since we have to traverse from root to leaves for all the possible paths. Break if the found.
        return findPathsSum_DFS( root, 0 ,  sum,  new ArrayList<>(), new ArrayList<>());
    }
    private boolean  findPathSum_DFS(TreeNode root, int currentPathSum, int targetPathSum){
        if (root != null) {
            if (root.val + currentPathSum == targetPathSum && root.left == null && root.right == null){
                return true;
            }
        }
        else return false;
        if (root.left != null) {
            boolean depth = findPathSum_DFS(root.left,root.val + currentPathSum, targetPathSum);
            if (depth) return true ;
        }
        if (root.right != null) {
            boolean depth = findPathSum_DFS(root.right, root.val + currentPathSum, targetPathSum);
            if (depth) return true;
        }
        return false;
    }
    private List<List<Integer>>   findPathsSum_DFS(TreeNode root, int currentPathSum, int targetPathSum, List<Integer> currList, List<List<Integer>> output){
        if (root != null) {
            if (root.val + currentPathSum == targetPathSum && root.left == null && root.right == null){
                currList.add(root.val);
                output.add(currList);
                return output;
            }
        }
        if (root.left != null) {
            List<Integer> newCurrentList = new ArrayList<>(); newCurrentList.addAll(currList); newCurrentList.add(root.val);
            findPathsSum_DFS(root.left,root.val + currentPathSum, targetPathSum,newCurrentList,output);
        }
        if (root.right != null) {
            List<Integer> newCurrentList = new ArrayList<>(); newCurrentList.addAll(currList); newCurrentList.add(root.val);
            findPathsSum_DFS(root.right,root.val + currentPathSum, targetPathSum, newCurrentList,output);

        }
        return output;
    }
    /**
     * https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
         boolean deleteRoot = deleteIntersectingNode_LessThanLimit_DFS( root, 0 ,  limit);
         if (deleteRoot)  return null;
         return root;
    }
    private boolean  deleteIntersectingNode_LessThanLimit_DFS(TreeNode root, int currentPathSum, int targetPathSum) {
        boolean deleteLeftChild = false, deleteRightChild=false;
        if (root == null) return false;
        if (root.left != null) {
             deleteLeftChild = deleteIntersectingNode_LessThanLimit_DFS(root.left,root.val + currentPathSum, targetPathSum);
        }
        else deleteLeftChild = true;

        if (root.right != null) {
             deleteRightChild = deleteIntersectingNode_LessThanLimit_DFS(root.right, root.val + currentPathSum, targetPathSum);
        }else deleteRightChild = true;

        //Take decision to delete this root, left child only or right child only
        if (root.left == null && root.right == null) {
            if (root.val + currentPathSum < targetPathSum)
                return true;
            else
            return false;
        }
        else if (root.left == null && deleteRightChild || root.right == null && deleteLeftChild ) return true;
        else if (deleteLeftChild ) root.left = null;
        else if (deleteRightChild) root.right = null;

        return false;
    }
    /**
     * https://leetcode.com/problems/binary-tree-paths/
      */
    public List<String> binaryTreePaths(TreeNode root, String currentNodePath, List<String> output) {
        if (root.left != null) {
            String newCurrentPath = "";
            if (currentNodePath.isEmpty()) newCurrentPath = String.valueOf(root.val);
            else newCurrentPath = currentNodePath + "->" + root.val;
            binaryTreePaths(root.left, newCurrentPath, output);
        }
        if (root != null && root.right == null && root.left == null) {
            String newCurrentPath = "";
            if (currentNodePath.isEmpty()) newCurrentPath = String.valueOf(root.val);
            else newCurrentPath = currentNodePath + "->" + root.val;
            output.add(newCurrentPath );
        }
        if (root.right != null) {
            String newCurrentPath = "";
            if (currentNodePath.isEmpty()) newCurrentPath = String.valueOf(root.val);
            else newCurrentPath = currentNodePath + "->" + root.val;
            binaryTreePaths(root.right, newCurrentPath, output);
        }
        return output;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          //Node values are unique
          // Traverse the tree, store the node values and its parents in a list of pairs
          // Iterate through the list and trace back the parents from each nodes and come to a common parent
        if (root == null) return null;
        Map<TreeNode,TreeNode> BFSList = new HashMap<>();
        List<TreeNode> visited = new ArrayList<TreeNode>();
        Queue queue = new LinkedList<TreeNode>();

        queue.add(root);
        BFSList.put(root,root);
        visited.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            //right child
            if (node.left != null && !visited.contains(node.left)) {
                visited.add(node.left);
                if (visited.contains(p) && visited.contains(q)) break;
                BFSList.put(node.left,node);
                queue.add(node.left);
            }
            //right child
            if (node.right != null && !visited.contains(node.right)) {
                visited.add(node.right);
                if (visited.contains(p) && visited.contains(q)) break;
                BFSList.put(node.right,node);
                queue.add(node.right);
            }
        }
        List<TreeNode> parentsP = new ArrayList<>();parentsP.add(p);
        List<TreeNode> parentsQ = new ArrayList<>();parentsQ.add(q);

        Iterator<Map.Entry<TreeNode,TreeNode>> iterator = BFSList.entrySet().iterator();
        TreeNode pParent = BFSList.get(p); parentsP.add(pParent);
        TreeNode qParent = BFSList.get(q); parentsQ.add(qParent);
        while(pParent != BFSList.get(pParent)){
              pParent = BFSList.get(pParent); parentsP.add(pParent);
        }
        while(qParent != BFSList.get(qParent)){
            qParent = BFSList.get(qParent); parentsQ.add(qParent);
        }
        //Iterate through the parents list from the root and find the last matching parent
        int i = parentsP.size()-1;
        int j = parentsQ.size()-1;
        while (true){
            if (i>=0)pParent = parentsP.get(i);
            else {return pParent;}
            if (j>=0)qParent = parentsQ.get(j);
            else {return pParent;}
            i--; j--;
            if (pParent != qParent) return  BFSList.get(pParent);
        }
    }
    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);node0.left = null; node0.right=null;
        TreeNode node8 = new TreeNode(8);node8.left = null; node8.right=null;
        TreeNode node7 = new TreeNode(7); node7.left = null; node7.right=null;
        TreeNode node4 = new TreeNode(4);node4.left = null; node4.right=null;
        TreeNode nodeQ1 = new TreeNode(1);nodeQ1.left = node0; nodeQ1.right=node8;
        TreeNode node6 = new TreeNode(6);node6.left = null; node6.right=null;
        TreeNode node2 = new TreeNode(2);node2.left = node7; node2.right=node4;
        TreeNode nodeP5 = new TreeNode(5);nodeP5.left = node6; nodeP5.right=node2;
        TreeNode node3 = new TreeNode(3);node3.left = nodeP5; node3.right=nodeQ1;

        new TreeOperations().lowestCommonAncestor(node3,nodeP5,node4);

    }

}
// An object of Res is passed around so that the
// same value can be used by multiple recursive calls.
class Res {
    public int val;
}