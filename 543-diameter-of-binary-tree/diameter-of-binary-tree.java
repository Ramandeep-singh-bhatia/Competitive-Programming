/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 /*
    The diameter of the binary tree will be the length of longest path for left subtree + right subtree as the binary tree will only have 2 children
    Longest path for a node to the left or right will be the height of the subtree i.e. from the node to the leaf node. 
    For a node at a certain level, the maximum height will be the maximum of either the left or right subtree and when we canculate the height of the parent, it will be 1 + the height of its node. 
    We have to find the node in a tree such that the overall leftHeight + rightHeight sum is maximum. We can do this easily by doing DFS where for each node, we have to traverse till the leaf node, get the maximum height and return back the height which contriutes to the height of its parent and so on.
    Time O(N)
    Space O(H). Height could be log N for balanced tree or N for skewed tree
 */
class Solution {
    int length = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return length;
    }

    public int helper(TreeNode root){
        if(root == null)
            return 0;
        
        int leftHeight = helper(root.left);
        int rightHeight = helper(root.right);

        length = Math.max(length, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}