class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int result = Integer.MIN_VALUE, sum = 0;
        int right = 0, left = 0;
        int length = 0;
        for(; right < nums.length; right++){
            length++;
            sum += nums[right];
            if(length > k){
                length--;
                sum -= nums[left];
                left++;
            }
            if(length == k)
                result = Math.max(result, sum);
            
        }

        return (double) result/k;
    }
}