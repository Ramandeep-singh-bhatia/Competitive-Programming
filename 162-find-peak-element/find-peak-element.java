/*
    Linear Scan - Time - O(n)
    Initial though was that to find the peak element, we will have to check if the element is greater than both the left and the right element but we can get the peak element by simply checking its next element.
    Case 1 - It is possible that the elements are in ascending order and the peak element would be the last element. If we verify if the element is greater than its next element, we can get the peak element.
    Case 2 - Elements are in decending order which emans the peak will be the first element. When we try to do a linear scan and find that the first element is greater than its neighbout, we get the peak element.
    Case 3 - Element will be in the middle. In this case , we traverse till we reach an element which is greater than the next element. We don't have to check the previous element as this is case 1 that we discussed. Similarly from here it will be case 2 where the current element will always be larger than the next element hence we don't have to check for the previous element.
    Time - O(n)
    Space - O(1)
*/

/*public class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }
        return nums.length - 1;
    }
}*/

/*
    When we do binary search , we exit the loop when l == r, hence we can either return l or r
    In binary search when we find a mid and compare it with its neightbour we know that there could be 2 cases
    Case 1 - mid is the element in the decending elements array
    Case 2 - mid is in the ascending element array.

    Case 1 - When mid is in the decending array and we check if the num at mid is greater than the next element. In decending it will be true, whcih means that either the mid is a peak or peak will be in the elements to the left. So we move right pointer to mid.
    Case 2 - When mid is in ascending array and we check if num[mid] is greater than the next element, it will be false as it is ascending order. Now the peak will be either the enxt element or the element to the right. So we make right as mid + 1.
    We keep reducing the search space until we reach to a point where we have just 1 element. This will be when left == right. At this point we know that this is the peak. so we return either left or right
    Time - O(n)
    Space - O(1)
*/

class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int mid = (l + r) / 2;
            if(nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }
}