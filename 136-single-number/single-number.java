class Solution {
    public int singleNumber(int[] nums) {
        // 2*(a + b + c) - (a +a + b + b + c)
        int sum1 = 0;
        int sum2 = 0;
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            if(!set.contains(num)){
                sum1 += num;
                set.add(num);
            }

            sum2 += num;
        }

        return 2* sum1 - sum2;
    }
}