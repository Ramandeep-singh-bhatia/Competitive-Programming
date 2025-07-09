// Two pass

/*class Solution {
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
}*/

// One pass

/*class Solution {
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
}*/

/*
    If we don't want to use frequency array, we can modify the nums array. 
    Since the number will always be within 1 to n, we know if we reach a num, we can make the index for that num to -1. Exmple if num is 3, we can go to the 3rd index and mark it -ve. If we find another 3 and the number on the index is already -ve we can add it to result
*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int num: nums){
            if(nums[Math.abs(num) - 1] < 0)
                result.add(Math.abs(num));
            nums[Math.abs(num) - 1] *= -1;
        }

        return result;
    }
}