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
    public TreeNode balanceBST(TreeNode root) {
        // Step 1: collect all node values in sorted order via inorder traversal
        List<Integer> sortedVals = new ArrayList<>();
        inorder(root, sortedVals);
        
        // Step 2: rebuild a balanced BST from the sorted values
        return buildBalanced(sortedVals, 0, sortedVals.size() - 1);
    }
    
    // inorder traversal: left -> root -> right gives ascending order in a BST
    private void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }
    
    // always pick the middle element as root so left and right halves are equal size (±1)
    // this guarantees the height difference between subtrees is at most 1
    private TreeNode buildBalanced(List<Integer> vals, int left, int right) {
        // base case: no elements in this range
        if (left > right) return null;
        
        // mid element becomes root - splits remaining elements as evenly as possible
        int mid = left + (right - left) / 2; // using this form to avoid overflow, though vals are small here
        TreeNode node = new TreeNode(vals.get(mid));
        
        // left subtree gets everything to the left of mid, right gets everything to the right
        node.left = buildBalanced(vals, left, mid - 1);
        node.right = buildBalanced(vals, mid + 1, right);
        
        return node;
    }
}