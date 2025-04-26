/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<TreeNode,TreeNode> parents;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        //Parents will contain the parents of each node. we will do dfs to find and populate the parents.
        parents = new HashMap<>();
        helper(root,null);
        
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        visited.add(target);
        q.add(target);
        // We will maintain the level and check with value of K. Once we reach level equal to K we will add that to the list ans and return it
        int level = 0;
        while(!q.isEmpty() && level <= K){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                // If level is equal to K we add it to the ans list else we do bfs
                // If the neighbours of the node is not in visited it means it is not visited and can be added to the list.
                // Apart from the neighbours we also check if the parent is not visited and add it to the list.
                // We increment the level after each iteration.
                if(node != null){
                    if(level == K) {
                        ans.add(node.val);
                        continue;
                    } 

                    if(node.left != null && !visited.contains(node.left)) {
                        visited.add(node.left);
                        q.add(node.left);
                    }
                    if(node.right != null && !visited.contains(node.right)) {
                        visited.add(node.right);
                        q.add(node.right);
                    }
                    if(parents.containsKey(node) && !visited.contains(parents.get(node))) {
                        visited.add(parents.get(node));
                        q.add(parents.get(node));
                    }
                }
            }
            level++;
        }
        
        return ans;
    }
    
    public void helper (TreeNode node, TreeNode parent) {
        parents.put(node,parent);
        if(node.left != null) helper(node.left,node);
        if(node.right != null) helper(node.right,node);
    }
}