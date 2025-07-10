/*
    Brute force - O(n^2)
*/

/*class Solution {
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(nums[i] == nums[j])
                    return true;
            }
        }

        return false;
    }
}*/

/*
    Sorting - O(nlogn)
*/
/*class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++){
            if(nums[i] == nums[i+1])
                return true;
        }

        return false;
    }
}*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            if(!set.add(num))
                return true;
            //set.add(num);
        }
        return false;
    }
}

