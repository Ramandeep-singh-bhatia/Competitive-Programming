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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        int col = 0, maxCol = 0,  minCol = 0;
        q.add(new Pair<>(root, col));
        while(!q.isEmpty()){
            Pair<TreeNode, Integer> p = q.poll();
            root = p.getKey();
            col = p.getValue();
            if(root != null){
                if(!map.containsKey(col))
                    map.put(col, new ArrayList<>());
                map.get(col).add(root.val);

                maxCol = Math.max(maxCol, col);
                minCol = Math.min(minCol, col);
            
                q.add(new Pair<>(root.left, col - 1));
                q.add(new Pair<>(root.right, col + 1));
            }
        }

        for(int i = minCol; i <= maxCol; i++){
            result.add(map.get(i));
        }

        return result;
    }
}