class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n == 0){
            result.add(List.of(lower, upper));
            return result;
        }
        if(nums[0] > lower)
            result.add(List.of(lower, nums[0]-1));
        for(int i = 0; i < n - 1; i++){
            if(nums[i] >= lower && nums[i+1] <= upper && nums[i+1] - nums[i] > 1){
                result.add(List.of(nums[i] + 1, nums[i+1] - 1));
            }
        }
        if(nums[n - 1] < upper){
            result.add(List.of(nums[n-1] + 1, upper));
        }
        return result;
    }
}