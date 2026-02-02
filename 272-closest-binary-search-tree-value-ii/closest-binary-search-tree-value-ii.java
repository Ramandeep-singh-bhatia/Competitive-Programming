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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        inorder(root,distance);

        distance.sort((a,b) -> Double.compare(Math.abs(a - target) , Math.abs(b - target)));

        for(int i = 0; i < k; i++){
            result.add(distance.get(i));
        }

        return result;
    }

    private void inorder(TreeNode node, List<Integer> distance){
        if(node == null)
            return;

        inorder(node.left, distance);
        distance.add(node.val);
        inorder(node.right, distance);
    }
}