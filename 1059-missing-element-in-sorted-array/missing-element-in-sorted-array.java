/*
    Time - O(logn)
    Space - O(1)
*/

class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n - 1;
        
        while (left <= right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] - nums[0] - mid < k) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        /* At the end of the loop, left = right + 1, and the kth missing is in-between arr[right] and arr[left]. 
        For the example [2,3,4,7,11], k = 5, after the binary search is over, left is 4 and right is 3.
        Also after binary search we know 
        1. The kth missing number must be after arr[right] but before or at arr[left]
        2. The number of missing positives before arr[right] is arr[right] - right - arr[0]
        3. We need to find the kth missing number, so we need to go k - (arr[right] - right - arr[0]) positions beyond arr[right]
        
        kth missing = arr[right] + (k - (arr[right] - right - arr[0]))
            = arr[right] + k - arr[right] + right + arr[0]
            = k + right + arr[0]
            */
        
        return nums[0] + k + right;
    }
}