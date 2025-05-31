class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0; 
        long sum = 0;
        int i = 0, j= 0;
        Arrays.sort(nums);
        for(; j < nums.length; ++j){
            long target = nums[j];
            sum += nums[j];
            while((j - i + 1) * target - sum > k)
                sum -= nums[i++];
            ans = Math.max(ans, j -i +1);
        }
        return ans;
    }
}