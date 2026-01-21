//Memoization
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        Integer[][] dp = new Integer[n][3];

        int costR = costs[0][0] + helper(costs, 1, 0, dp);
        int costB = costs[0][1] + helper(costs, 1, 1, dp);
        int costG = costs[0][2] + helper(costs, 1, 2, dp);

        return Math.min(costR, Math.min(costB, costG));
    }

    private int helper(int[][] costs, int houseNum, int prevHouseColor, Integer[][] dp){
        if(houseNum >= costs.length){
            return 0;
        }

        if(dp[houseNum][prevHouseColor] != null){
            return dp[houseNum][prevHouseColor];
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            if(i == prevHouseColor)
                continue;
            
            int cost = costs[houseNum][i] + helper(costs, houseNum + 1, i, dp);
            result = Math.min(result, cost);
        }
        dp[houseNum][prevHouseColor] = result;
        return result;
    }
}

// Tabulation
/*class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;

        if(n == 1){
            return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
        }

        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for(int i = 1; i < n; i++){
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);

            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);

            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }
}*/
// Space optimized DP
/*class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;

        if(n == 1){
            return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
        }

        int prevR = costs[0][0];
        int prevB = costs[0][1];
        int prevG = costs[0][2];

        for(int i = 1; i < n; i++){
            int currR = costs[i][0] + Math.min(prevB, prevG);

            int currB = costs[i][1] + Math.min(prevR, prevG);

            int currG = costs[i][2] + Math.min(prevR, prevB);

            prevR = currR;
            prevG = currG;
            prevB = currB;
        }

        return Math.min(prevR, Math.min(prevB, prevG));
    }
}*/