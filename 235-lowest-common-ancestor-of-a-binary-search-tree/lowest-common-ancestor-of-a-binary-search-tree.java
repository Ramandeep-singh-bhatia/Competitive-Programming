/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==null || root == p || root == q)
            return root;

        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        if(leftResult != null && rightResult != null){
            return root;
        }

        return leftResult != null ? leftResult : rightResult;
    }
}*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;

        while(current != null){
            if(p.val < current.val && q.val < current.val){
                current = current.left;
            } else if(p.val > current.val && q.val > current.val){
                current = current.right;
            } else {
                return current;
            }
        }

        return null;
    }
}