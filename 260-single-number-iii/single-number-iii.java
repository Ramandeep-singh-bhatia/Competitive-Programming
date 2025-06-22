class Solution {
    public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        Map<Integer, Integer> frequency = new HashMap<>();
        int i = 0;
        for(int num: nums){
            frequency.put(num, frequency.getOrDefault(num,0) + 1);
        }

        for(int num: nums){
            if(frequency.get(num) == 1){
                result[i++] = num;
            }
        }

        return result;
    }
}