/*class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        // Can't assign at least one job per day
        if (n < d) return -1;

        return solve(jobDifficulty, 0, d);
    }

    // Returns min difficulty to schedule jobs[start..end] across daysLeft days
    private int solve(int[] jobs, int start, int daysLeft) {
        int n = jobs.length;

        // Base case: last day, must take all remaining jobs
        // Cost is just the max of everything from start to end
        if (daysLeft == 1) {
            int maxDiff = 0;
            for (int i = start; i < n; i++) {
                maxDiff = Math.max(maxDiff, jobs[i]);
            }
            return maxDiff;
        }

        int minCost = Integer.MAX_VALUE;

        // Track the max difficulty of today's jobs as we extend the slice
        int todayMax = 0;

        // Try giving today jobs[start], jobs[start..start+1], etc.
        // Must leave at least (daysLeft - 1) jobs for remaining days
        for (int end = start; end <= n - daysLeft; end++) {
            todayMax = Math.max(todayMax, jobs[end]);

            // Recurse on remaining jobs with one fewer day
            int rest = solve(jobs, end + 1, daysLeft - 1);

            minCost = Math.min(minCost, todayMax + rest);
        }

        return minCost;
    }
}*/

/*class Solution {
    // memo[start][daysLeft] = min difficulty to schedule jobs[start..n-1] in daysLeft days
    // -1 means not yet computed
    private int[][] memo;

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) return -1;

        // n * d possible states, initialize to -1 to indicate uncomputed
        memo = new int[n][d + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solve(jobDifficulty, 0, d);
    }

    private int solve(int[] jobs, int start, int daysLeft) {
        int n = jobs.length;

        // Base case: last day, must take all remaining jobs, cost is their max
        if (daysLeft == 1) {
            int maxDiff = 0;
            for (int i = start; i < n; i++) {
                maxDiff = Math.max(maxDiff, jobs[i]);
            }
            return maxDiff;
        }

        // Return cached result if we've solved this subproblem before
        if (memo[start][daysLeft] != -1) return memo[start][daysLeft];

        int minCost = Integer.MAX_VALUE;

        // Running max of today's jobs as we extend the slice rightward
        int todayMax = 0;

        // Try assigning jobs[start..end] to today
        // Must leave at least (daysLeft - 1) jobs for the remaining days
        for (int end = start; end <= n - daysLeft; end++) {
            todayMax = Math.max(todayMax, jobs[end]);

            int rest = solve(jobs, end + 1, daysLeft - 1);

            minCost = Math.min(minCost, todayMax + rest);
        }

        // Cache before returning
        memo[start][daysLeft] = minCost;
        return minCost;
    }
}*/

/*class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) return -1;

        // dp[i][j] = min difficulty to schedule first i jobs in exactly j days
        // using n+1 size for i so dp[i] naturally means "first i jobs"
        int[][] dp = new int[n + 1][d + 1];

        // Fill with large value to indicate uncomputed/impossible states
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Base case: 0 jobs in 0 days costs nothing
        dp[0][0] = 0;

        // Fill for each number of days j from 1 to d
        for (int j = 1; j <= d; j++) {
            // i = number of jobs scheduled so far, need at least j jobs for j days
            for (int i = j; i <= n; i++) {
                // Try all possible starting points k for day j
                // day j handles jobs[k..i-1]
                // k must be at least j-1 so first j-1 days have at least one job each
                int todayMax = 0;
                for (int k = i - 1; k >= j - 1; k--) {
                    // Extend today's slice leftward, update running max
                    // going right to left so we build max incrementally
                    todayMax = Math.max(todayMax, jobDifficulty[k]);

                    // Only combine if previous days state is reachable
                    if (dp[k][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + todayMax);
                    }
                }
            }
        }

        return dp[n][d] == Integer.MAX_VALUE ? -1 : dp[n][d];
    }
}*/

class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) return -1;

        // prev[i] = min difficulty to schedule first i jobs in exactly j-1 days
        int[] prev = new int[n + 1];
        // curr[i] = min difficulty to schedule first i jobs in exactly j days
        int[] curr = new int[n + 1];

        // Initialize both arrays to large value to represent impossible states
        Arrays.fill(prev, Integer.MAX_VALUE);
        Arrays.fill(curr, Integer.MAX_VALUE);

        // Base case: 0 jobs in 0 days costs nothing
        prev[0] = 0;

        for (int j = 1; j <= d; j++) {
            // Reset curr for this day's computation
            Arrays.fill(curr, Integer.MAX_VALUE);

            // Need at least j jobs to have j days with one job each
            for (int i = j; i <= n; i++) {
                int todayMax = 0;
                // Try all split points k - day j handles jobs[k..i-1]
                for (int k = i - 1; k >= j - 1; k--) {
                    // Extend today's window leftward, update running max
                    todayMax = Math.max(todayMax, jobDifficulty[k]);

                    // Only combine if previous state is reachable
                    if (prev[k] != Integer.MAX_VALUE) {
                        curr[i] = Math.min(curr[i], prev[k] + todayMax);
                    }
                }
            }

            // curr becomes prev for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        // After d iterations, answer is in prev[n]
        return prev[n] == Integer.MAX_VALUE ? -1 : prev[n];
    }
}