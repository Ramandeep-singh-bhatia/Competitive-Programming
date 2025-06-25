class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int sum = 0;
        for(int num: nums){
            if(num == 0){
                result = Math.max(result, sum);
                sum = 0;
            } else {
                sum += num;
            }
        }
        return Math.max(result, sum);
    }
}