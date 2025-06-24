/*
    https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/3066636/weird-kadane-explanation-with-images
*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int minSum = 0;
        int maxSum = 0;
        int totalSum = 0;
        for(int num: nums){
            minSum += num;
            maxSum += num;
            totalSum += num;
            max = Math.max(max, maxSum);
            min = Math.min(min, minSum);

            if(maxSum < 0)
                maxSum = 0;
            if(minSum > 0)
                minSum = 0;
        }

        int circularSum = totalSum - min;

        if(circularSum == 0)
            return max;

        return Math.max(max, circularSum);
    }
}