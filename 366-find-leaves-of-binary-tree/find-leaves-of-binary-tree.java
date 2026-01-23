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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(root, result);
        return result;
    }

    private int dfs(TreeNode node, List<List<Integer>> result){
        if(node == null)
            return -1;

        int leftHeight = dfs(node.left, result);
        int rightHeight = dfs(node.right, result);

        int currentHeight = 1 + Math.max(leftHeight, rightHeight);

        if(result.size() == currentHeight)
            result.add(new ArrayList<>());

        result.get(currentHeight).add(node.val);

        return currentHeight;
    }
}