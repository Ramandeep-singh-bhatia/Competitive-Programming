/*
    For a given set of integer if we put it in a set and do a for loop, we go to each
number and see if we have the next num in the set. if yes we increment the currnum
and currStreak by 1. and maintain the longestStreak. But this is still O(n*n) as we
go to 3 we still count 3 and then 4, when we go to 2, we still coult 2,3,4. To avoid 
this we can check if for a given number we havea number 1 less than the current num
we don't need to process the streak as it will never form the longest streak

Like for 4, if there is a 3, we know the longer streak will start from 3 than from 4
Sililarly for 2, it should start from 2, not from 3 or 4
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        Set<Integer> num = new HashSet<>();
        for(int i : nums){
            num.add(i);
        }
        int result = 0;
        for(int x : num){
            if(!num.contains(x-1)){
                int count = 1;

                while(num.contains(++x)){
                    count++;
                    //x++;
                }
                result = Math.max(result, count);
            }
                
            
        }
        return result;
    }
}