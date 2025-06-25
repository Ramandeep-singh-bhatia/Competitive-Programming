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
/*class Solution {
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
}*/

/*
    Where ever we have to find the maximum of minimum or maximum of minimum we should think of binary search
    Lets start with the linear search.
    We have to split the array into k subarrays and find the minimum among the max sum of that split subarrays. If we have 5 elements and we have to divide the array into 2 we have multiple options as follows
    1  /  2,3,4,5   Sum - 1 / 14    Maximum sum - 14
    1,2  /  3,4,5   Sum - 3 / 12    Maximum sum - 12
    1,2,3 /  4,5    Sum - 6 / 9     Maximum sum - 9
    1,2,3,4  / 5    Sum - 10 / 5    Maximum sum - 10

Minumum among those is 9 so 9 is the answer. If we think of brute force we can start with 1 and say this the maximum subrray sum allowed and try to split the arrays. if we notice we cannot place 2,3,4 or 5 in any split as it exceeds the maximum allowed sum so it does not make sense to take 1. So we start with 2 as maximum and try to split, we again can't have 3,4or 5 in any split. The question has suggested to use all the elemnts and we cannot have emplty suarray so the minimum value we should consider for the maximum sum for the subarray is the maximum value in the srray. In this case it will be 5.
    When we take 5 as the maximum value allowed, we can split the array as 1,2 / 3 / 4 / 5 which is 4 subarrays but we only need to split into 2 so we can move forward and try with 6 as the maximum sum and try to split it. We keep on doing until we find a maximum sum that splits the array into 2 parts (because k is 2 here).
    We also will need the maximum value allowed for the loop. It could be the maximum sum of the array which is sum of all the numbers. So we loop from max value in the array uptil the sum. Every tme we split, we check how many subarrays we forma nd then we can return as soon as we find the first sum that splits the array into k or less subarrays which will the minimum sum value.

*/
// Linear Search

/*class Solution { 
    public int splitArray(int[] nums, int k) {
        int min = -1;
        int sum = 0;
        int splitNum = 0;
        for(int num: nums){
            min = Math.max(min, num);
            sum += num;
        }

        for(int arraySum = min; arraySum <= sum; arraySum++){
            splitNum = getSplitCount(nums, arraySum);

            if(splitNum <= k){
                return arraySum;
            } 
        }

        return -1;
    }

    private int getSplitCount(int[] nums, int arraySum){
        int splitNum = 1;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(sum + nums[i] <= arraySum){
                sum += nums[i];
            } else {
                sum = nums[i];
                splitNum++;
            }
        }

        return splitNum;
    }
}*/

/*
    we are checking for each array sum between min and sum and tryign to find the array sum which will cause k splits. Instead of linear search we can do binary search between mid and sum. Id the split is <= k then we have to move to the left to find split that are closer to k. If the split is greater than k, we have to move right.

    Example - Nums - 1,2,3,4,5 and k = 2
    the array sum will range from 5 and 15. If array sum is 5, we can split the array into 1,2 / 3 / 4 / 5 which is 4 splits and if we check for 15, we have only 1 split. So for 5 we get maximum splits and for 15 we get minimum splits. So if we take mid of 5 and 15 we get 10 and we can split the array as follows - 1,2,3 / 4,5. This is exact 2 splits so to find the minimum we move to the left to see if there is any other number that gives us 2 splits. we make the right as 9 and find mid of 5 and 9. It would be 7. For 7 we can split array as follows - 1,2,3 / 4 / 5. it is now 3 splits so we have to move right so we make left as 8. In the end we get 9 as the answer.

    Time - if s is the range for binary search which is between mid and sum, the binary search will be log s. We have to call the getSplitCount function which takes O(n) so overall time complexity is O(n* log S)
    Space - O(1)
*/

// Binary Search

class Solution { 
    public int splitArray(int[] nums, int k) {
        int min = -1;
        int sum = 0;
        int splitNum = 0;
        for(int num: nums){
            min = Math.max(min, num);
            sum += num;
        }

        int result = 0;
        int left = min;
        int right = sum;

        while(left <= right){
            int arraySum = left + (right - left)/2;
            splitNum = getSplitCount(nums, arraySum);

            if(splitNum <= k){
                right = arraySum - 1;
                result = arraySum;
            } else {
                left = arraySum + 1;
            }
            
        }

        return result;
    }

    private int getSplitCount(int[] nums, int arraySum){
        int splitNum = 1;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(sum + nums[i] <= arraySum){
                sum += nums[i];
            } else {
                sum = nums[i];
                splitNum++;
            }
        }

        return splitNum;
    }
}