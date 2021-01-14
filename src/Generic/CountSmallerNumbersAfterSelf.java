package Generic;

import java.util.ArrayList;
import java.util.List;

public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
       // While inserting a new key in an AVL tree, we first compare the key with root. If key is greater than root, then it is
        //greater than all the nodes in left subtree of root. So we add the size of left subtree to the count of smaller element for the key being inserted.
        BSTNode bst = new BSTNode(nums[nums.length-1]);
        SelfBalancingBSTree avlTree = new SelfBalancingBSTree();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(0);
        }
        for(int i=nums.length-2; i>=0;i--){
            bst = avlTree.insert(bst, nums[i]);
        }

        return result ;
    }

    public static void main(String args[]){
        int [] input = {5,2,6,1};
        new CountSmallerNumbersAfterSelf().countSmaller(input);
    }
}
