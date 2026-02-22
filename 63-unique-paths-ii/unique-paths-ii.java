/*class Solution {
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
}*/

/*class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // if start or end is blocked, no paths exist
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return 0;

        // dp[i][j] = number of unique paths to reach grid[i-1][j-1]
        // extra row and column of zeros act as implicit base cases -
        // any "above" the first row or "left" of the first column is naturally 0
        int[][] dp = new int[m+1][n+1];

        // starting cell - one way to be here before we've moved anywhere
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // skip start cell since we already initialized it
                if (i == 1 && j == 1) continue;

                // obstacle in the corresponding grid cell means 0 paths through here
                if (grid[i-1][j-1] == 1) {
                    dp[i][j] = 0;
                } else {
                    // paths from above + paths from the left
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        // answer corresponds to bottom-right corner of the grid
        return dp[m][n];
    }
}*/

class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // if start or end is blocked, no paths exist
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return 0;

        // single row dp array - dp[j] will hold the number of paths to reach
        // the current row's cell at column j
        int[] dp = new int[n];

        // starting cell has one path to reach it
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // obstacle - no paths through this cell regardless of what came before
                if (grid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    // dp[j] currently holds value from previous row (paths from above)
                    // dp[j-1] was just updated this iteration (paths from left)
                    // so dp[j] += dp[j-1] naturally combines both contributions
                    dp[j] += dp[j-1];
                }
                // if j == 0 and no obstacle, dp[j] already holds the correct value
                // from the previous row - no update needed since there's no left neighbor
            }
        }

        // dp[n-1] now holds the answer for the bottom-right corner
        return dp[n-1];
    }
}