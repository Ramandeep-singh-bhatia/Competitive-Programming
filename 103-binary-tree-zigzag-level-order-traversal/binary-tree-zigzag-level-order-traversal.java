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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<Integer> l = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while(!q.isEmpty()){
            l = new ArrayList<>();
            int n = q.size();
            for(int i = 0; i < n; i++){
                TreeNode value = q.poll();
                if(level % 2 == 1){
                    l.add(value.val);
                }else
                    l.addFirst(value.val);
                
                if(value.left != null){
                    q.add(value.left);  
                }
                if(value.right != null){
                    q.add(value.right);
                }    
            }
            level++;
            ans.add(l);
        }
        
        return ans;
    }
}