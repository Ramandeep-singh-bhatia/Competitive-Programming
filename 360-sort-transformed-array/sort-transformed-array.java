/*class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++){
            result[i] = a* nums[i] * nums[i] + b * nums[i] + c;
        }

        Arrays.sort(result);

        return result;
    }
}*/

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];

        int index = 0;
        int left = 0;
        int right = n - 1;

        if( a >= 0) {
            index = n - 1;
            while(left <= right) {
                int leftval = helper(nums[left], a, b, c);
                int rightval = helper(nums[right], a, b, c);

                if(leftval >= rightval){
                    result[index] = leftval;
                    left++;
                } else {
                    result[index] = rightval;
                    right--;
                }

                index--;
            }
        } else {
            while(left <= right){
                int leftval = helper(nums[left], a, b, c);
                int rightval = helper(nums[right], a, b, c);

                if(leftval <= rightval){
                    result[index] = leftval;
                    left++;
                } else {
                    result[index] = rightval;
                    right--;
                }

                index++;
            }
        }
        return result;
    }

    private int helper(int x, int a, int b, int c){
        return a * x * x + b * x + c;
    }
}

