package Generic; /**
 * Definition for singly-linked list. public class SinglyListNode { int val; SinglyListNode next; SinglyListNode(int
 * x) { val = x; } }
 */



/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
class ListToBST
{
    private SinglyListNode head;

    private int findSize(SinglyListNode head)
    {
        SinglyListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }
    /**
     * Convert Inorder list to BST
     */
    private TreeNode convertSortedLinkedListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertSortedLinkedListToBST(l, mid - 1);
        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;
        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;
        // Recurse on the right hand side and form BST out of them
        node.right = this.convertSortedLinkedListToBST(mid + 1, r);
        return node;
    }
    public TreeNode sortedSortedListToBST(SinglyListNode head)
    {  // Get the size of the linked list first
        int size = this.findSize(head);
        this.head = head;
        // Form the BST now that we know the size
        return convertSortedLinkedListToBST(0, size - 1);
    }
    public TreeNode preOrderListToBST(SinglyListNode head){
        int size = this.findSize(head);
        this.head = head;
        return convertPreOrderListToBST(0, size - 1);
    }
    public TreeNode preOrderArrayToBST(int [] nums){
        int size = nums.length;
        return convertPreOrderArrayToBST(nums, 0, size - 1);
    }
    private TreeNode convertPreOrderListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        // First process the current node
        TreeNode node = new TreeNode(this.head.val);
        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;
        // Then, Recursively form the left half
        node.left = this.convertPreOrderListToBST(l+1, mid );
        // Recurse on the right hand side and form BST out of them
        node.right = this.convertPreOrderListToBST(mid + 1, r);

        return node;
    }
    private TreeNode convertPreOrderArrayToBST(int [] nums, int start, int end) {
        // Invalid case
        if (start > end) {
            return null;
        }
        // First process the current node
        TreeNode node = new TreeNode(nums[start]);

        // search the index of first element in current range of preorder
        // sequence which is larger than the value of root node
        int right;
        for (right = start; right <= end; right++) {
            if (nums[right] > node.val) {
                break;
            }
        }
        // Then, Recursively form the left half
        node.left = this.convertPreOrderArrayToBST(nums, start+1, right-1);
        // Recurse on the right hand side and form BST out of them
        node.right = this.convertPreOrderArrayToBST(nums, right, end);

        return node;
    }
}