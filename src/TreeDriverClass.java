public class TreeDriverClass
{
    public  static void main(String[] args)
    {
        TreeNode one = new TreeNode(2);

        TreeNode two = new TreeNode(5);
        one.left = two;
        TreeNode three = new TreeNode(4);
        one.right = three;

        TreeNode four = new TreeNode(7);
        two.left = four;
        TreeNode five = new TreeNode(10);
        two.right = five;
        TreeNode six = new TreeNode(9);
        three.left = six;
        TreeNode seven = new TreeNode(8);
        three.right = seven;

       // System.out.println(new TreeOperations().BFS(one));
        System.out.println(new TreeOperations().DFSInorder(one));

    }
}
