class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n+1];
        for(int num: nums){
            freq[num]++;
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(freq[i] == 2)
                result.add(i);
        }

        return result;
    }
}