/*class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num: nums){
            if(!freq.containsKey(num)){
                freq.put(num, 0);
            }
            freq.put(num, freq.get(num) + 1);
            
        }

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            if(entry.getValue() > n/2)
                return entry.getKey();
        }

        return -1;
    }
}*/

class Solution {
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 0;
        for(int num : nums){
            if(count == 0)
                result = num;
            count += result == num ? 1 : -1;
        }

        return result;
    }
}