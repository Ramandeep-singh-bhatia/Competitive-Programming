/*
    Naive Solution
    Time O(n)
    Space O(1) - Only use result array
*/

/*class Solution {
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
}*/

/*
    Prefix/suffix product solution. 
    3 pass.
    Time - O(n)
    Space - O(n)
*/

/*class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];

        prefix[0] = 1;
        suffix[n-1] = 1;

        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i-1] * nums[i-1];
        }

        for(int i = n-2; i >= 0; i--){
            suffix[i] = suffix[i+1] * nums[i+1];
        }

        for(int i = 0; i < n; i++){
            result[i] = prefix[i] * suffix[i]; 
        }

        return result;
    }
}*/

/*
    Better Prefix suffix product solution
    2 pass
    Time - O(n)
    Space - O(1) / Only use result array
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;

        for(int i = 1; i < n; i++){
            result[i] = result[i-1]*nums[i-1];
        }

        int right = 1;

        for(int i = n-1; i>=0; i--){
            result[i] = result[i]*right;
            right *= nums[i];
        }

        return result;
    }
}