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
    public boolean isCompleteTree(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer( root );
        
        TreeNode prevNode = root;
        
        // Launch level-order traversal
        while( queue.size() > 0 ){
            
            TreeNode curNode = queue.poll();
            
            if( curNode != null ){
                
                if( prevNode == null ){
                    // Empty node in the middle means this is not a complete binary tree ( not left-compact)
                    return false;
                }
                
                queue.offer( curNode.left );
                queue.offer( curNode.right );
            }
            // udpate previous node
            prevNode = curNode;
        }
        return true;
    }
}