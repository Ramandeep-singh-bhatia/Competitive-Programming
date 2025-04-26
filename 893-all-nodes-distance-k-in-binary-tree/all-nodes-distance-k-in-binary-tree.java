/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*class Solution {
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
}*/

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        helper(root, null, graph);

        List<Integer> answer = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        // Add the target node to the queue with a distance of 0
        queue.add(new int[] { target.val, 0 });
        visited.add(target.val);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], distance = cur[1];

            // If the current node is at distance k from target,
            // add it to the answer list and continue to the next node.
            if (distance == k) {
                answer.add(node);
                continue;
            }

            // Add all unvisited neighbors of the current node to the queue.
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[] { neighbor, distance + 1 });
                }
            }
        }

        return answer;
    }

    // Recursively build the undirected graph from the given binary tree.
    private void helper(TreeNode cur, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (cur != null && parent != null) {
            graph.putIfAbsent(cur.val, new ArrayList<>());
            graph.putIfAbsent(parent.val, new ArrayList<>());
            graph.get(cur.val).add(parent.val);
            graph.get(parent.val).add(cur.val);
        }

        if (cur != null && cur.left != null) {
            helper(cur.left, cur, graph);
        }

        if (cur != null && cur.right != null) {
            helper(cur.right, cur, graph);
        }
    }
}