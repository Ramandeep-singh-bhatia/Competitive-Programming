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
/*class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if( root == null)
            return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }
}*/

class Solution {
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while(node != null) {
            if(node.right == null){
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode temp = node.right;
                while(temp.left != null && temp.left != node){
                    temp = temp.left;
                }

                if(temp.left == null){
                    temp.left = node;
                    node = node.right;
                } else {
                    sum += node.val;
                    node.val = sum;
                    temp.left = null;
                    node = node.left;
                }
            }
        }
        return root;
    }
}