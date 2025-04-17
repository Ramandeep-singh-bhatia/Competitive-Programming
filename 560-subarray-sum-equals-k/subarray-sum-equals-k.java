//Brute Force

/*class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum == k)
                    result++;
            }
        }

        return result;
    }
}*/
// Cumulative sum
/*class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1];
        int result = 0;
        sum[0] = 0;

        for(int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }

        for(int i = 0; i < n; i++){
            for(int j = i+1; j<= n; j++){
                if(sum[j] - sum[i] == k) {
                    result++;
                }
            }
        }

        return result;
    }
}*/

/*
    The key insight here involves prefix sums (cumulative sums) and how they relate to subarray sums.
Let's break down the logic:

For any array position, we keep track of the cumulative sum up to that point (sum[i]).
If we have two indices i and j where sum[i] - sum[j] = k, this means the sum of elements from index j+1 to index i is exactly k.
Mathematically: If sum[i] - sum[j] = k, then we can rearrange to sum[j] = sum[i] - k. So we need to check if sum[i] - k has occurred previously.

The algorithm works as follows:

We iterate through the array once, maintaining a running sum.
For each position, we check if (sum - k) exists in our HashMap.
If it does, we've found one or more subarrays ending at the current position with sum k.
We add the current sum to our HashMap (or increment its frequency if it already exists).

Let's trace through a simple example:
nums = [1, 2, 3], k = 3

Start with map = {0:1} (representing empty subarray), sum = 0, count = 0
i=0: sum = 1, check if (1-3)=-2 exists in map (no), add {1:1} to map
i=1: sum = 3, check if (3-3)=0 exists in map (yes, frequency 1), count = 1, add {3:1} to map
i=2: sum = 6, check if (6-3)=3 exists in map (yes, frequency 1), count = 2, add {6:1} to map

Final count = 2, corresponding to subarrays [1,2] and [3].
The time complexity is O(n) because we're making just one pass through the array, and HashMap operations are O(1) on average.
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum,0) + 1);
        }
        return count;
    }
}