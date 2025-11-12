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

/*
    In actual algorithm, the result will represent a potential majority element and we will have to reiterate the array to check if the result element is the majority element or not by checking its frequency is > n/2 or not. But in this question it is mentioned that there qill always be a majority element so we can say that whatever is coming in result will definatly be the majority element. That is why there is no check and we return result after intital iteration.

    Time - O(n)
    Space - O(1)
*/

class Solution {
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 0;
        for(int num : nums){
            if(count == 0)
                result = num;
            if(result == num)
                count++;
            else
                count--;
        }

        return result;
    }
}