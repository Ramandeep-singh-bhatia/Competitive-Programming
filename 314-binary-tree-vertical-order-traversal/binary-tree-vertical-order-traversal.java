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

 /*
    Time - O(N)
    Space - O(N)
 */
/*class Solution {
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
}*/

/*
    Time - O(W HlogH) - O(W) for the width of the tree which we will loop from incolumn to max. We then sort the list in the map using depth which is height so HlogH for that
*/
class Solution {
    int minColumn = 0, maxColumn = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        
        Map<Integer, List<int[]>> map = new TreeMap<>();
        dfs(root,0,0,map);
        
        for(int i = minColumn; i < maxColumn + 1; ++i){
            Collections.sort(map.get(i),(a,b)-> a[0]-b[0]);
             List<Integer> temp = new ArrayList<>();
            for(int[] val: map.get(i)){
                temp.add(val[1]);
            }
            result.add(temp);
        }
        return result;
    }
    

    
    private void dfs(TreeNode root,int depth,int column,Map<Integer, List<int[]>> map ){
        if(root == null)
            return;
        
        if(!map.containsKey(column)){
            map.put(column,new ArrayList<>());
        }
        
        map.get(column).add(new int[]{depth,root.val});
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);
        dfs(root.left,depth+1,column-1,map);
        dfs(root.right,depth+1,column+1,map);
    }
}