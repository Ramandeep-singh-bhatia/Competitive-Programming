/*
    We have a graph with directed edges and there would be n-1 edges, meaning we don't have a cycle. Now for the edge where we have a direction we can reach from node 1 to node 2, but if we want to move from node 2 to node 1, we will ahve to reverse the edge. We have to find the number of edges we have to reverse for each node to reach all the other nodes in the graph. 
    Earlier I thought that we can take each node in the graph one by one and try to reach other nodes and keep counting the edge reversal but it will be inefficient because we will be repeating the edge revarsal count for nodes. meaning if we have a path from node 1 to node 5 where we calculate the number of edge revarsals, then it does not make sense to try to find the number of edge revarsals from node 2 - node 5 , node 3 - node 5 and node 4 - node 5. If we keep track of the cost between nodes, we can make use of that information to find the cose between nodes that have these nodes in common. May be like memoization. 

            1 <- 6
            |
            v
       7 -> 2 <- 3 -> 4 -> 5

    For a graph once we find the cost to reach node 1 - node 5 which is 1 and we also have a total cost which is 3, then to find the cost from node 5 - node 1 all we need to do is depth - cost. How that works. cost incurred from 1 - 5 is 1 becoz we had to reverse the edge between 2 - 3. The depth would be 4 and cost is 1. If suppose I have to find the cost from 5 to 1, we have to reverse 3 edgeswhich is from 1-2, 3-4 and 4-5. and depth(4) - cost(1) is also 3. So this will help us calculate the cost from 5-1. 
    Now total cost to reach all the edges from 1 is 3 and the cost to reach from 1 - 5 is 1, if we want to find the cost to reach 1 from other edge we can remove the edge cost that we have calulated i.e. from 1 - 5 and the remaining will be 3 - 1 wich is 2. If we ignore the cost from 1- 5 to reach other edges from 1 the cost would be 2 which is 1 to reach 6 and 1 to reach 7.
    Earlier we calculated that the cost to reach 1 from 5 is 2 and we know that if we ignore this path the remaining total cost to reach other edges from 1 is 2, so we can say that to reach all the edges from 5 the total cost would be cost from 5 -> 1 + cost from 1 to all the other nodes which is 2 + 2 = 4. 
    So in short, if we calculate the cost required from 1 node to all the other nodes, we can calculate the cost from all the nodes using depth and the 2nd formula and we don't have to traverse the tree multiple times for each node. 
*/

/*class Solution {
    int totalCost;
    public int[] minEdgeReversals(int n, int[][] edges) {
        int[] result = new int[n];
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for(int[] edge: edges){
            if(!graph.containsKey(edge[0]))
                graph.put(edge[0], new HashMap<>());
            graph.get(edge[0]).put(edge[1], 0);

            if(!graph.containsKey(edge[1]))
                graph.put(edge[1], new HashMap<>());
            graph.get(edge[1]).put(edge[0], 1);
        }
        boolean[] visited = new boolean[n];
        int[] depth = new int[n];
        int[] cost = new int[n];

        totalCost = 0;
        dfs(0, graph, visited, cost, 0, depth);
        result[0] = totalCost;

        for(int i = 1; i < n; i++){
            int val = depth[i] - cost[i];
            int val2 = totalCost - cost[i];
            result[i] = val + val2;
        }

        return result;
    }

    private void dfs(int source, Map<Integer, Map<Integer, Integer>> graph, boolean[] visited, int[] cost, int d, int[] depth){
        visited[source] = true;
        depth[source] = d;

        for(Map.Entry<Integer, Integer> entry: graph.get(source).entrySet()){
            int destination = entry.getKey();
            int c = entry.getValue();
            if(!visited[destination]){
                cost[destination] = cost[source] + c;
                totalCost += c;
                dfs(destination, graph, visited, cost, d + 1, depth);
            }
        }
    }
}*/

/*import java.util.*;

public class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        // Map<node, Map<neighbor, cost>>
        // cost 0 = going with edge direction (no reversal needed)
        // cost 1 = going against edge direction (reversal needed)
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // traversing u->v is free, already points this way
            graph.get(u).put(v, 0);
            // traversing v->u costs 1, need to reverse
            graph.get(v).put(u, 1);
        }
        
        int[] answer = new int[n];
        
        for (int start = 0; start < n; start++) {
            answer[start] = dfs(start, -1, graph);
        }
        
        return answer;
    }
    
    private int dfs(int node, int parent, Map<Integer, Map<Integer, Integer>> graph) {
        int totalCost = 0;
        
        for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
            int neighbor = entry.getKey();
            int cost = entry.getValue();
            
            // skip parent to avoid going back up the tree
            if (neighbor == parent) continue;
            
            // cost to reach this neighbor + cost to reach everything beyond it
            totalCost += cost + dfs(neighbor, node, graph);
        }
        
        return totalCost;
    }
}*/

import java.util.*;

public class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        // Map<node, Map<neighbor, cost>>
        // cost 0 = going with edge direction (no reversal needed)
        // cost 1 = going against edge direction (reversal needed)
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // traversing u->v is free, already points this way
            graph.get(u).put(v, 0);
            // traversing v->u costs 1, need to reverse
            graph.get(v).put(u, 1);
        }
        
        int[] answer = new int[n];
        
        // Pass 1: compute answer[0] by rooting tree at node 0
        answer[0] = dfs(0, -1, graph);
        
        // Pass 2: re-rooting DFS to propagate answer to all other nodes
        reroot(0, -1, graph, answer);
        
        return answer;
    }
    
    // Same DFS as brute force but just for node 0
    private int dfs(int node, int parent, Map<Integer, Map<Integer, Integer>> graph) {
        int totalCost = 0;
        
        for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
            int neighbor = entry.getKey();
            int cost = entry.getValue();
            
            if (neighbor == parent) continue;
            
            totalCost += cost + dfs(neighbor, node, graph);
        }
        
        return totalCost;
    }
    
    // Re-rooting DFS: derive answer for each node from its parent's answer
    private void reroot(int node, int parent, Map<Integer, Map<Integer, Integer>> graph, int[] answer) {
        for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
            int neighbor = entry.getKey();
            int cost = entry.getValue();
            
            if (neighbor == parent) continue;
            
            // cost is from node's perspective going toward neighbor
            // if cost == 0, edge goes node->neighbor
            //   when we re-root at neighbor, this edge now needs a reversal
            //   so neighbor pays +1 more than node
            // if cost == 1, edge goes neighbor->node
            //   when we re-root at neighbor, this edge is now free
            //   so neighbor pays -1 less than node
            if (cost == 0) {
                answer[neighbor] = answer[node] + 1;
            } else {
                answer[neighbor] = answer[node] - 1;
            }
            
            reroot(neighbor, node, graph, answer);
        }
    }
}