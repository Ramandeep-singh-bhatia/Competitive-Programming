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

class Solution {
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
}