package Generic;

import java.util.ArrayList;
import java.util.List;

// Java program for insertion in AVL Tree, Tree Map is a java implementation for it
class BSTNode {
    int key, height;
    BSTNode left, right;
    int countKey;

    BSTNode(int d) {
        key = d;
        height = 1;
        countKey++;
    }
}

public class SelfBalancingBSTree {

    BSTNode root;

    // A utility function to get the height of the tree 
    int height(BSTNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers 
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    BSTNode rightRotate(BSTNode y) {
        BSTNode x = y.left;
        BSTNode T2 = x.right;

        // Perform rotation 
        x.right = y;
        y.left = T2;

        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root 
        return x;
    }

    // A utility function to left rotate subtree rooted with x 
    //   x--> first node where there is imbalance up the path , y -->right child of x, T2--> left sub tree of y, others remain same
    //x is the root of subtree for which rotation has to be done
    //https://www.youtube.com/watch?v=n917xdxKSmo, but with x and y in reverse positions
    BSTNode leftRotate(BSTNode x) {
        BSTNode y = x.right;
        BSTNode T2 = y.left;

        // Perform rotation 
        y.left = x;
        x.right = T2;

        // Update heights 
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root 
        return y;
    }

    // Get Balance factor of node N 
    int getBalance(BSTNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    BSTNode insert(BSTNode node, int key) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new BSTNode(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        else // Duplicate keys not allowed 
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right)); 

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case 
        if (balance < -1 && key > node.right.key)
            // A utility function to left rotate subtree rooted with node
            //   node--> first node where there is imbalance up the path
            return leftRotate(node);

        // Left Right Case 
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case 
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }
    int count(BSTNode tree)
    {
        int c ;             //Node itself should be counted
        if (tree ==null)
            return 0;
        else
        {   c =  tree.countKey;
            c += count(tree.left);
            c += count(tree.right);
            return c;
        }
    }

    BSTNode insertAndUpdateCoun(BSTNode node, int key, int arrayIndex, List<Integer> list) {
        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new BSTNode(key));

        if (key < node.key)
            node.left = insertAndUpdateCoun(node.left, key,arrayIndex, list);
        else if (key > node.key) {
            list.set(arrayIndex, ( list.get(arrayIndex) + count(node.left)) + node.countKey);
            node.right = insertAndUpdateCoun(node.right, key,arrayIndex,list);
        }
        else // Duplicate keys not allowed
        {
            node.countKey++;
            list.set(arrayIndex, ( list.get(arrayIndex) + count(node.left)));
            return node;
        }
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there    are 4 cases

        // //Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal 
    // of the tree. 
    // The function also prints height of every node 
    void preOrder(BSTNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public static void main(String[] args) {
        int [] input = {23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        new SelfBalancingBSTree().countSmaller(input);
    }
    public List<Integer> countSmaller(int[] nums) {
        // While inserting a new key in an AVL tree, we first compare the key with root. If key is greater than root, then it is
        //greater than all the nodes in left subtree of root. So we add the size of left subtree to the count of smaller element for the key being inserted.
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            result.add(0);
        }

        for(int i=nums.length-1; i>=0;i--){
            root = insertAndUpdateCoun(root, nums[i],i,result);
        }
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        preOrder(root);
        return result ;
    }
}
