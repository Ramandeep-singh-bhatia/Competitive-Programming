class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n+1];
        List<Integer> result = new ArrayList<>();
        for(int num: nums){
            if(freq[num] == 1)
                result.add(num);
            freq[num]++;
        }

        return result;
    }
}