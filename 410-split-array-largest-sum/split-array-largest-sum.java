/*
    The question asks us to split the array into k parts take the maximum subarray sum among those split and then take the minimum of that maximum sum.
    Firstly lets focus on splitting. We know that there can be k splits we can either decide to choose a value to be part of the split or ignore the number. For each split we calculate the largest sum and then find the minimum sum among those. Looks like for split we can use 0-1 knapsack concept of dp. 
    We also have to calculate the sum of all the numbers that are part of the split. It does not make sense to calculate the sum for each split using loop so we optimize by having a prefix sum and then we can calulate the sum between index on the go.

    we can have index and k along with prefix sum for recursion. BASE  condition -  will be when k == 1 which means that we have k splits. why? because if k is 2 then we have to do 2 splits and whne k is 1 meaning we no longer need to split the array. Whne k == 1 we have split the arrays as required so we calculate the right part from the split index. We need the sum of the split to calculate the maximum among those.
    at a certain index if we have more splits left, all we have to do is loop through the remaining prefix sum array to further split it. With each split we have to either include the number in the split, or skip it. Since we are checking for subarrays, by skip it means that we no longer check for the rest of the array. So all we have to do is check if we are adding it to the first split. Everytime we include it in the split, we have to add it to the sum and then move forward to the next value by calling the method again. 
    For all the aplits we calculate the largest sum and then we maintain a variable that takes the minimum among those largest sums. at any point if we have found a subarray whose sum becomes larger than the minimum largest sum so far, we can skip checking the rest of the numbers in the array

    Time complexity - O(n^2*k) - O(n*k) for the memoization as we have n*k states. For each state we loop through till n, so it will be n^2 * k
    Space complexity - O(n*k)
*/

// Top down approach
/*class Solution {
    int[][] dp;
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n+1];
        dp = new int[n+1][k+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i], -1);
        }
        prefixSum[0] = 0;

        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }

        return getMinLargestSum(prefixSum, prefixSum.length - 1, 0, k);
    }

    private int getMinLargestSum(int[] prefixSum, int n, int index, int k){
        if(dp[index][k] != -1)
            return dp[index][k];
        if(k == 1){
            return dp[index][k] = prefixSum[n] - prefixSum[index];
        }

        int minLargestSum = Integer.MAX_VALUE;
        for(int i = index; i <= n - k; i++){
            int sum = prefixSum[i + 1] - prefixSum[index];

            int largestSum = Math.max(sum, getMinLargestSum(prefixSum, n, i + 1, k -1));

            minLargestSum = Math.min(minLargestSum, largestSum);

            if(sum >= minLargestSum)
                break;
        }
        return dp[index][k] = minLargestSum;
    }
}*/
// Bottom up Approach
class Solution {
    int[][] dp;
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n+1];
        dp = new int[n+1][k+1];
        
        prefixSum[0] = 0;

        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }

        for(int j = 1; j <= k; j++){
            for(int index = 0; index < n; index++){
                if(j == 1){
                    dp[index][j] = prefixSum[n] - prefixSum[index];
                    continue;
                }
                int minLargestSum = Integer.MAX_VALUE;
                for(int i = index; i <= n - j; i++){
                    int sum = prefixSum[i + 1] - prefixSum[index];

                    int largestSum = Math.max(sum, dp[i + 1][j - 1]);

                    minLargestSum = Math.min(minLargestSum, largestSum);

                    if(sum >= minLargestSum)
                        break;
                }
                dp[index][j] = minLargestSum;
            }
        }
        return dp[0][k];
    }
}