class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int left = 0, right = 0;
        long result = 0;
        long sum = 0;
        Set<Integer> set = new HashSet<>();
        for(; right < nums.length; right++){
            
            while(set.size() == k || set.contains(nums[right])){
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }

            sum += nums[right];
            set.add(nums[right]);
            if(set.size() == k){
                result = Math.max(result, sum);
            }
        }

        return result;
    }
}