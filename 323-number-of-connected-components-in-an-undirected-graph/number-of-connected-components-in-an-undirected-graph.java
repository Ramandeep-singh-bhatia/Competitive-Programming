/*class Solution {
    public int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> adjList = new ArrayList<>();

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i =0; i < n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                result++;
                stack.push(i);

                while(!stack.isEmpty()){
                    int num = stack.pop();
                    visited[num] = true;
                    for(int neighbour: adjList.get(num)){
                        if(!visited[neighbour])
                            stack.push(neighbour);
                    }
                }
            }
        }
        return result;
    }
}*/

class Solution {
    // Parent array for Union-Find
    private int[] parent;
    // Rank array for union by rank optimization
    private int[] rank;
    
    public int countComponents(int n, int[][] edges) {
        // Initialize Union-Find structure
        parent = new int[n];
        rank = new int[n];
        
        // Initially, each node is its own parent (n components)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        // Process each edge and union the components
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        
        // Count the number of distinct components
        int components = 0;
        for (int i = 0; i < n; i++) {
            // A node is a root if it's its own parent
            if (parent[i] == i) {
                components++;
            }
        }
        
        return components;
    }
    
    // Find with path compression
    private int find(int node) {
        if (parent[node] != node) {
            // Path compression: make parent point directly to root
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
    
    // Union by rank
    private void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        
        // Already in same component
        if (root1 == root2) {
            return;
        }
        
        // Union by rank: attach smaller tree under larger tree
        if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            // Same rank: arbitrarily choose one as parent and increment rank
            parent[root2] = root1;
            rank[root1]++;
        }
    }
}