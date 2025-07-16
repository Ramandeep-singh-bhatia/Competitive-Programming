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
    List<Integer> arr = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        return inorder(root).get(k-1);
    }

    private List<Integer> inorder(TreeNode root){
        if(root == null)
            return null;
        inorder(root.left);
        arr.add(root.val);
        inorder(root.right);

        return arr;
    }
}