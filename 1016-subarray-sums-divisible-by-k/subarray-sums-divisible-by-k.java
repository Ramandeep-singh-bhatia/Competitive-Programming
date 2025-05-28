/*class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int result = 0;
        int[] cumulativeSum = new int[n+1];
        cumulativeSum[0] = 0;
        for(int i = 1; i <=n; i++){
            cumulativeSum[i] = cumulativeSum[i-1] + nums[i-1];
        }

        for(int i = 0; i < n; i++){
            for(int j = i+1; j <= n; j++){
                if((cumulativeSum[j] - cumulativeSum[i]) % k == 0)
                    result++; 
            }
        }

        return result;
    }
}*/

/*
    Two cases to handle.
    1. remainder is -ve: if we get sum % k as -1 and lets say k is 5 and sum is -1. 
            Without correction - Frequency map will store -1: 1. Now we add 5 to sum and it becomes 4. Frequency map will add 4:1 but the result will not be updated. Technically , we got a 5 between two sums which means we have a subarray that is divisible by 5. So for nums[-1,5] we get frequency map [0:1, -1:1, 4:1] and result is 0 but is should not be the case. 
            With Correction - To deal with the -ve remainder, we will have to convert it into a +ve number before checking int he map. Frequency map will have [0:1]. When -1 is added to sum, we do correction and add k to it to make it +ve. So frequency map will store [0:1, 4:1]. Next when we add 5 to sum we get -1 +5 which is 4. When we check in map, we already have 4 in the map, so it updates result to 1 and the map will store [0:1, 4:2].
            Lets say we had another 5 i.e. the nums - [-1,5,5]. When we get another 5, we already have 4 in the map so the result will be 1 + 2 = 3. which means we got 3 subarrays which are divisible by k which is 5 in this case and the subarrays would be [5], [5] and [5,5] 

        2. why we need to do frequency.put(0,1) before we begin. Lets say we encounetr out first 0 remainder and we don't have 0:1 in the map. We will not be incrementing the result but technically if we have encountered 0 for the first time, we have found the sum divisible by k so we have to increment the result.

*/

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> frequency = new HashMap<>();
        frequency.put(0,1);
        int sum = 0, result = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            int mod = sum % k;
            if(mod < 0)
                mod += k;
            if(frequency.containsKey(mod))
                result += frequency.get(mod);

            frequency.put(mod, frequency.getOrDefault(mod,0)+1);
        }

        return result;
    }
}