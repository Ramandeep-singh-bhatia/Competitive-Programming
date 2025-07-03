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
class Solution {
    int pathSum;
    public int maxPathSum(TreeNode root) {
        pathSum = Integer.MIN_VALUE;
        helper(root);
        return pathSum;
    }

    private int helper(TreeNode root){
        if(root == null)
            return 0;
        int leftPathSum = Math.max(helper(root.left),0);
        int rightPathSum = Math.max(helper(root.right),0);

        pathSum = Math.max(pathSum, leftPathSum + rightPathSum + root.val);

        return root.val + Math.max(leftPathSum, rightPathSum);
    }
}