/*
BruteForce
 Time - If k is very small, then time complexity will be O(n*k). Because for each element we will be checking the previous k elements 
        If k is large compared to n, time complexity will be O(n^2) because you can't compare more than n elements.

        Space O(1)
*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j - i <= k && j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
