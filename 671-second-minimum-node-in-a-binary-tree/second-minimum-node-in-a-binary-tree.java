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
Because we have a constariant that node value can be <= 2^31 - 1 which is Integer.MAX_VALUE. It makes sense to have result as Long.MAX_VALUE
*/

class Solution {
    long result = Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        int minVal = root.val;
        
        dfs(root, minVal);

        return result == Long.MAX_VALUE ? -1 : (int)result;
    }

    public void dfs(TreeNode root, int minVal) {
        if (root == null) return;
        if(root.val > minVal && root.val < result) {
            result = root.val;
        }
        dfs(root.left, minVal);
        dfs(root.right, minVal);

    }
}