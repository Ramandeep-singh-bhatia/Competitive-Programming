class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int num: nums){
            /*if(!count.containsKey(num)){
                count.put(num, 0);
            }
            int c = count.get(num);*/
            count.put(num, count.getOrDefault(num , 0) + 1);
        }

        for(int num: nums){
            if(count.get(num) == 1)
                return num;
        }

        return 0;
    }
}