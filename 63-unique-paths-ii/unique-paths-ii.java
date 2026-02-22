class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // if start or end is blocked, no paths exist
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return 0;
        
        // memo table to cache results for each cell, -1 means not computed yet
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }
        
        return solve(grid, 0, 0, m, n, memo);
    }
    
    private int solve(int[][] grid, int row, int col, int m, int n, int[][] memo) {
        // out of bounds - not a valid path
        if (row >= m || col >= n) return 0;
        
        // hit an obstacle - this path is blocked
        if (grid[row][col] == 1) return 0;
        
        // reached destination - found one valid path
        if (row == m - 1 && col == n - 1) return 1;
        
        // already computed this cell before - reuse the cached result
        if (memo[row][col] != -1) return memo[row][col];
        
        // compute paths from here and cache before returning
        memo[row][col] = solve(grid, row + 1, col, m, n, memo) 
                       + solve(grid, row, col + 1, m, n, memo);
        
        return memo[row][col];
    }
}