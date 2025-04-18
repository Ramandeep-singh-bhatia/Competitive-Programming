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

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(map.containsKey(sum%k)) {
                if (i - map.get(sum%k) > 1)
                    return true;
            } else {
                map.put(sum%k, i);
            }
        }

        return false;
    }
}

/*
Intuition - 

Keep track of the remainder of the sum at each index.
Suppose we have a number 23 like in example [23,2,4,6,7], the remainder of 23/6(k) or 23 % 6 is 5.
Now if we add any number in multiple of 6 the remainder is still 5. then means if we keep track of the
    remainder, as soon as we get the remainder again, we know that we have added the numbers into the
    cumulative sum which is divisible by 6.
Another condition is that the sum should be of atleast 2. So we will have to keep track of the index 
    for each remainder. We can have a map which has the remainder of the sum i.e. sum %k  and the index.
As soon as we find another instance of the same remainder, we verify if the current index - i (index 
    stored for the previous instance of the remainder) is greater than 1 which means it will be atleast 2
    we return true
If we do not find the same remainder again return false
*/

/*class Solution {
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
}*/