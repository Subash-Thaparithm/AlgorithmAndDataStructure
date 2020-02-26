import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode
  {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class TreeOperations
{
    List<Integer> result = new ArrayList<>();
    List<TreeNode> visited = new ArrayList<TreeNode>();
    Queue queue = new LinkedList<TreeNode>();

    public boolean isBalanced(TreeNode root)
    {
        boolean isBalanced = false;

        if(root == null) return true;

        int rh = height(root.right);
        int lh = height(root.left);

        if(((rh-lh)==1 ||(lh-rh==1) || (rh==lh)) && (isBalanced(root.left)) && (isBalanced(root.right)))
            return true;

        return isBalanced;

    }
    private int height(TreeNode node)
    {
        if(node == null) return 0;
        int rh =0;
        int lh =0;

        if(node.right != null)  rh = height(node.right);
        if(node.left != null)   lh = height(node.left);

        return Math.max(rh,lh)+1;


    }

    public List<Integer> BFS(TreeNode root) // Iterative
    {
      if(root== null) return null;

      List<Integer> result = new ArrayList<>();
      List<TreeNode> visited = new ArrayList<TreeNode>();
      Queue queue = new LinkedList<TreeNode>();


        queue.add(root);
        result.add(root.val);
        visited.add(root);

        while(! queue.isEmpty())
        {
            TreeNode node = (TreeNode)queue.poll();
            //right child
            if(node.left != null && !visited.contains(node.left))
            {
                visited.add(node.left);
                result.add(node.left.val);
                queue.add(node.left);
            }
            //right child
            if(node.right != null && !visited.contains(node.right))
            {
                visited.add(node.right);
                result.add(node.right.val);
                queue.add(node.right);
            }
        }

        return result;
    }

    public List<Integer> DFSInorder(TreeNode root) // Recursive
    {
       if(root != null)
       {
           result.add(root.val);
       }

       if(root.left != null) DFSInorder(root.left);
       if(root.right != null) DFSInorder(root.right);

       return result;
    }


}