/*
    n = 1 -> 1 way to reach the end
    n = 2 -> 2 ways to reach the end -> (1 steps,1 steps), 2 steps
    n = 3 - > (1 steps, 1 steps, 1 steps) , (1 steps, 2 steps) , (2 steps, 1 steps) so 3 ways
    n = 4 -> (1 steps, 1 steps, 1 steps, 1 steps), (1 steps, 1 steps, 2 steps), (1 steps, 2 steps, 1 steps), (2 steps, 1 steps, 1 steps), (2 steps, 2 steps) so 5 ways
*/


class Solution {
    int[] dp;
    public int climbStairs(int n) {
        dp = new int[n+1];
        return helper(n);
    }

    private int helper(int n){
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;

        if(dp[n] > 0)
            return dp[n];
        dp[n] = helper(n-1) + helper(n-2);

        return dp[n];
    }
}

/*class Solution {
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}*/

/*class Solution {
    public int climbStairs(int n) {
        //int[] dp = new int[n+1];
        int prev = 1;
        int curr = 1;
        for(int i = 2; i <= n; i++){
            int temp = curr;
            curr = prev + curr;
            prev = temp;
            
        }

        return curr;
    }
}*/