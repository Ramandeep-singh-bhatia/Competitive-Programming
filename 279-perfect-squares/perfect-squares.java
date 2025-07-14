/*class Solution {
    public int numSquares(int n) {
        List<Integer> squareNums = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squareNums.add(i * i);
        }
        return helper(n, squareNums);
    }

    private int helper(int k, List<Integer> squareNums) {
        // Base case: if k is a perfect square
        if (squareNums.contains(k)) {
            return 1;
        }
        int minNum = Integer.MAX_VALUE;
        for (int square : squareNums) {
            if (k < square) 
                break;
            int newNum = helper(k - square, squareNums) + 1;
            minNum = Math.min(minNum, newNum);
        }
        return minNum;
    }
}*/

/*class Solution {
    int[] dp;
    public int numSquares(int n) {
        List<Integer> squareNums = new ArrayList<>();
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        for (int i = 1; i * i <= n; i++) {
            squareNums.add(i * i);
        }
        return helper(n, squareNums);
    }

    private int helper(int k, List<Integer> squareNums) {
        // Base case: if k is a perfect square
        if (squareNums.contains(k)) {
            dp[k] = 1;
            return 1;
        }

        if(dp[k] != -1)
            return dp[k];
        int minNum = Integer.MAX_VALUE;
        for (int square : squareNums) {
            if (k < square) 
                break;
            int newNum = helper(k - square, squareNums) + 1;
            minNum = Math.min(minNum, newNum);
        }
        return dp[k] = minNum;
    }
}*/


class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        if(n == 1)
            return 1;
        for(int j = 1; j <= n; j++){
            for(int i = 1; i*i <= n; i++){
                if(j >= i*i)
                    dp[j] = Math.min(dp[j], dp[j - i*i] + 1);
            }
        }

        return dp[n];
    }
}