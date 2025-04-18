class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int product = 1;
        int countZeros = 0;
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            if(nums[i] == 0)
                countZeros++;
            else {
                product *= nums[i];
            }
        }
        for(int i = 0; i < n; i++){
            if(countZeros > 1){
                result[i] = 0;
            } else if(countZeros == 1) {
                result[i] = nums[i] == 0 ? product : 0;
            } else {
                result[i] = product/nums[i];
            }
        }
        return result;
    }
}