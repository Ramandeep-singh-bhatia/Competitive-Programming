// Naive linear approach
/*
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return nums[0];
        if(nums[0] != nums[1])
            return nums[0];
        if(nums[n-2] != nums[n-1])
            return nums[n-1];
        for(int i = 1; i < n-1; i++){
            if(nums[i-1] != nums[i] && nums[i] != nums[i+1])
                return nums[i];
        }
        return -1;
    }
} */

// Brute force linear

/*
    class Solution {
        public int singleNonDuplicate(int[] nums) {
            int n = nums.length;
            
            for(int i = 0; i < n-1; i+=2){
                if(nums[i] != nums[i+1])
                    return nums[i];
            }
            return nums[n-1];
        }
    }
*/

/*
Binary search - 
    
    Example 1 - 1,1,2,2,3,3,4. In this example the mid will be 3 and if the value to the mid is equal to the value to mid - 1, we see that the single value is to the right so we go right

    Example 2 - 1,2,2,3,3,4,4. In this example the mid will be 3 and if the value to the mid is equal to the value to mid + 1, we see that the single value is to the left so we go left
    
    Example 3 - 1,1,2,3,3,4,4,5,5. In this example the mid will be 4 and if the value to the mid is equal to the value to mid - 1, as per example 1 the single value should be to the right so we go right, but it is to the left.
    
    Example 4 - 1,1,2,2,3,3,4,5,5. In this example the mid will be 4 and if the value to the mid is equal to the value to mid + 1, as per example 2 the single value should be to the left so we go left, but it is to the right.

    In both example 1 and example 3, the main difference is the number of values to the left and right. In a way it will depend not only on the value next to the mid but also on the number of values. In example 1, the number of values to the left and right are odd [1,1,2],2,[3,3,4]. and in example 2 the number of values to the left and right are even [1,1,2,3],3,[4,4,5,5]. If we see closer to example 1, if nums[mid] == nums[mid - 1] and the number of values to the left are odd, we can say that 1 value is equal to nums[mid and remaining values are now even meaning that they are in pairs so the single value will be on the right. In example 2, we got even number of values on left and right and when we compare mid with mid - 1, we saw that the number of values after removing the mid - 1 will be odd meaning that 1 value has to be single on the left because the mid-1 is in pair with mid, so we go left. we do mid - 2 because we have already checked mid with mid - 1 so it makes sense to skip mid - 1. Same goes with the other 2 examples. 

    Time complexity - O(log n)
    Space complexity - O(1)

*/

class Solution {
    public int singleNonDuplicate(int[] nums) {
       
        int left = 0, right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left) / 2;
            boolean isEven = (right - mid) %2 == 0;
            if(nums[mid] == nums[mid + 1]){
                if(isEven)
                    left = mid + 2;
                else
                    right = mid - 1;
            } else if (nums[mid] == nums[mid - 1]){
                if(isEven)
                    right = mid - 2;
                else
                    left = mid + 1;
            } else 
                return nums[mid];
        }
        return nums[left];
    }
}

