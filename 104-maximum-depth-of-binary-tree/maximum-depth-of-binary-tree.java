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
/*lass Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null)
            return 1 + maxDepth(root.right);
        if(root.right == null)
            return 1 + maxDepth(root.left);
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}*/

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            depth++;
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
            }
        }
        return depth;
    }

}