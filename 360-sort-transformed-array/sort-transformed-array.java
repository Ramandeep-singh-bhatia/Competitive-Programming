class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++){
            result[i] = a* nums[i] * nums[i] + b * nums[i] + c;
        }

        Arrays.sort(result);

        return result;
    }
}