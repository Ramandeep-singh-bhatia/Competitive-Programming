class Solution {
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
}