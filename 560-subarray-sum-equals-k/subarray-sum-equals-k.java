//Brute Force

class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = nums[i];
            if(sum == k)
                result++;
            for(int j = i + 1; j < nums.length; j++){
                sum += nums[j];
                if(sum == k)
                    result++;
            }
        }

        return result;
    }
}

/*class Solution {
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
}*/