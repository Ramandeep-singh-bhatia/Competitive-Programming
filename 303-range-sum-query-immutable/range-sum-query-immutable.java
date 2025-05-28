/*
    When we canculate the prefix sum, the index 3 represents the sum of 0th, 1st and 2nd index of the nums. Similarly the index 5 represents the the sum of 0th, 1st, 2nd, 3rd and 4th element in the nums array. Its because we have and extra element in the prefixsum and 0th index in prefix sum represents the sum of 0 elements. 
    Now when have to calculate the range sum from left to right, lets say from 2 to 5, the sum shoudl have the 2nd, 3rd, 4th and 5th element of nums array. So int cumulative sum array, the index 3 will have the sum of 0th, 1st and 2nd element and index 2 will have 0th and 1st element sum. Index 6 will have the cumulative sum from 0th index till 5th index of nums array. So calculate the sum from 2 to 5 we will have to subtract the cumulative sum index 6 with index 2 so that we have the sum from 2 to 5 in the nums array.
    Example - nums - [-2,0,3,-5,2,-1]]
    Cumulative sum- [0,-2,-2,1,-4,-2,-3]
    cumulative of 2-5 will be - 3 -5 +2 -1 = -1 which should be 6th index of cumulative sum (-3) - the 2nd index of cumulative sum (-2) which somes to -1 which is the answer.
    Time - NumArray - O(n), sumRange - O(1)
    Space - O(n)
*/

class NumArray {
    int[] cumulativeSum;
    public NumArray(int[] nums) {
        int n = nums.length;
        cumulativeSum = new int[n+1];
        cumulativeSum[0] = 0;
        for(int i = 1; i <= n; i++){
            cumulativeSum[i] = cumulativeSum[i-1] + nums[i-1];
        }
    }
    
    public int sumRange(int left, int right) {
        return cumulativeSum[right+1] - cumulativeSum[left]; 
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */