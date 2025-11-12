class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int[] count = new int[3];
        int idx = 0;
        for(int i = 0; i < n; i++){
            count[nums[i]]++;
        }

        for(int j = 0; j < 3; j++){
            while(count[j] > 0){
                nums[idx++] = j;
                count[j]--;
            }
        }
    }
}