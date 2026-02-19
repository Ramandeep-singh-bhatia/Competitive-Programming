/*class Solution {
    public int findCircleNum(int[][] isConnected) {
        //List<List<Integer>> adjList = new ArrayList<>();
        int n = isConnected.length;
        boolean[] visited = new boolean[n];

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        /*for(int i = 0 ; i < n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if (i != j && isConnected[i][j] == 1){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }*/

        /*for(int i = 0; i < n; i++){
            if(!visited[i]){
                result++;
                stack.push(i);
            }
                

            while(!stack.isEmpty()){
                int num = stack.pop();
                visited[num] = true;
                /*for(int neighbour: adjList.get(num)){
                    if(!visited[neighbour])
                        stack.push(neighbour);
                }*/
                /*for(int j = 0; j < n; j++){
                    if(!visited[j] && isConnected[num][j] == 1)
                        stack.push(j);
                }
            }
        }
        return result;
    }
}*/

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n]; // track which cities we've already placed in a province
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Found a new unvisited city — it starts a new province
                provinces++;
                dfs(isConnected, visited, i, n);
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city, int n) {
        // Mark this city as visited so we don't count it again
        visited[city] = true;

        // Look at every other city — this is row 'city' in the adjacency matrix
        for (int neighbor = 0; neighbor < n; neighbor++) {
            // If directly connected AND not yet visited, recurse into it
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor, n);
            }
        }
    }
}