class Solution {
    int numBoundaryOnes = 0;
    int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numOfOnes = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    numOfOnes++;
                }
            }
        }

        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1 && (i == 0 || j == 0 || i == m-1 || j == n-1)){
                    if(!visited[i][j]){
                        dfs(grid, visited, i , j, m ,n);
                    }
                }
            }
        }

        return numOfOnes - numBoundaryOnes;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i , int j, int m, int n){
        visited[i][j] = true;
        numBoundaryOnes++;
        for(int[] dir: directions){
            int ni = i + dir[0];
            int nj = j + dir[1];
            if(ni >= 0 && nj >= 0 && ni < m && nj < n && !visited[ni][nj] && grid[ni][nj] == 1){
                dfs(grid, visited, ni, nj, m , n);
            }
        }
    }
}