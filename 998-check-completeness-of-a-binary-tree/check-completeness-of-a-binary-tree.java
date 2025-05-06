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
}*/

/*
    Count number of nodes in the tree. tree has a property where the left child is at 2*i position and right child is 2*i + 1 position for a parent at ith position.
    We can keep track of the number of nodes and at any point the number of nodes are less than the index value for the node, we know there is a gap so we return false
*/

class Solution {
    public int countNodes(TreeNode root){
        if(root == null) 
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    public boolean dfs(TreeNode root,int total,int i){
        if(root == null) 
            return true;
        if(i>total) 
            return false;
        return dfs(root.left,total,2*i) && dfs(root.right,total,2*i+1);
    }
    public boolean isCompleteTree(TreeNode root) {
        int totalNodes = countNodes(root);
        return dfs(root,totalNodes,1);
    }
}