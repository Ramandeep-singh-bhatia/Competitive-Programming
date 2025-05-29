// Brute Force

/*class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1];
        sum[0] = 0; 
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }

        for(int i = 0; i < n; i++){
            for(int j = i+2; j <= n; j++){
                if((sum[j] - sum[i]) % k == 0)
                    return true;
            }
        }

        return false;
    }
}*/

/*
Intuition - 

Keep track of the remainder of the sum at each index.
Suppose we have a number 23 like in example [23,2,4,6,7], the remainder of 23/6(k) or 23 % 6 is 5.
Now if we add any number in multiple of 6 the remainder is still 5. then means if we keep track of the remainder, as soon as we get the remainder again, we know that we have added the numbers into the cumulative sum which is divisible by 6.
Another condition is that the sum should be of atleast 2. So we will have to keep track of the index for each remainder. We can have a map which has the remainder of the sum i.e. sum %k  and the index.
As soon as we find another instance of the same remainder, we verify if the current index - i (index stored for the previous instance of the remainder) is greater than 1 which means it will be atleast 2
    we return true
If we do not find the same remainder again return false

Why we need to initialize map with[0:-1]
    This is for a special case. For every remainder(sum%k), we keep track of the remainder and the index. Lets say we had 5,2,3 : k = 4 and we do not initialize map with 0,-1.
    When we get 5,the remainder is 0 and we add in the map 0,0 
    When we get 2, we have sum = 7 and remainder 2, so we add in the map 2,1
    When we get 3, we have sum 10 and remainder 0, we have 0,0 in the map so we calculate i - map.get(0) which is 2 - 0 which is > 1 so we return true which is correct.
    But what if we have nums as 2,3. Technically there are 2 elements which will form 5 and will be divisible by 5 so it shoudl return true. Lets see if this works without initialization of map to 0,-1
    When we get 2, we have sum = 2 and remainder 2, so we add in the map 2,0
    When we get 3, we have sum = 5 and remainder = 0, so we add in the map 0,1 and it returns false but it shoudl return true. This means that initailly we need to have the remainder 0 in the map but even if we have remainder 0 with 0th index the i - map.get(0) will be 1 - 0 and it will still return false. But if we see we have 2 elements 2,3 which is equal to sum 5 and remainder 0 but the calculation shows 1 element. So we need the map initialization to be o,-1 so that when we do i - map.get(0) we do i - (-1) which will be i + 1 representing the number of elments between 0th index and ith index
    The index -1 represents a virtual position before the array starts. when we find the prefix sum with remainder 0. If we are at the index i, the subarray from start to index i has the sum divisible by k and the length of this subarray is i - (-1) = i + 1
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(!map.containsKey(sum%k))
                map.put(sum%k, i);
            else {
                if(i - map.get(sum%k) > 1)
                    return true;
            }
        }
        return false;
    }
}