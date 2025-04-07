/*
    Top down approach
     Time - O(n^2)
     Space - o(n)
*/

class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int height(TreeNode node) {
        if(node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}