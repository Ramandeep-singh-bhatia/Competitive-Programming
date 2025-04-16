//https://leetcode.com/problems/maximum-subarray/solutions/1595097/java-kadane-s-algorithm-explanation-using-image

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, sum = 0;
        
        for(int i=0;i<n;i++){
            sum += nums[i];
            max = Math.max(sum,max);
            
            if(sum<0) sum = 0;
        }
        
        return max;
    }
}

/*
If We have to print the max subarray
    class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        
        int maxi = nums[0];
        int start = 0, end = 0; // the final start and end position of the maximum sum subarray
        
        int sum = 0;
        int s = 0; // the temporary start position
        
        for(int i = 0; i < n; i++){
            sum += nums[i];
      
            if(sum > maxi){
                maxi = sum;
                start = s;
                end = i;
            }
            
            if(sum < 0){
                sum = 0;
                s = i + 1;
            }   
        }
        
        System.out.println("Maximum Sum Subarray from nums[" + start + "] = " + nums[start] + " till nums[" + end + "] = " + nums[end]);
        
        return maxi; 
    }
}

*/