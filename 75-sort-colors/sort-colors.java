/*class Solution {
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
}*/

/*
    Above code is O(2n) time complexity. We can implement dutch flag algorithm for O(n) time complexity and O(1) space complexity
*/

class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high){
            if(nums[mid] == 0){
                swap(nums, mid, low);
                mid++;
                low++;
            } else if(nums[mid] == 1){
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap (int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
