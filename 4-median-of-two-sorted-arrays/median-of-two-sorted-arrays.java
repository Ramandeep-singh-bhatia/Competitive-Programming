/*
 For brute force we concatenated two arrays in a sorted way by comparing one element each in the arrays and making a new array. Then we check if the number of elements in the combined array is even or odd. If it is odd we know that the median will be the middle elemnt. if even then median will be average of  2 middle elements. i.e for number of elemnts 11, median will be 6th element. for number of elements 10, it should be average of 5th and 6th element in the combined sorted array.

  Time Complexity O(m+n)
  Space Complexity O(m+n)
  */

  /*class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] mergedNum = new int[m + n];
        int i = 0, j = 0, k = 0;
        while( i < m && j < n){
            if(nums1[i] <= nums2[j])
                mergedNum[k++] = nums1[i++];
            else
                mergedNum[k++] = nums2[j++];
        }
        while(i<m)
            mergedNum[k++] = nums1[i++];
        while(j<n)
            mergedNum[k++] = nums2[j++];

        //System.out.println(k);

        if(k%2 == 1)
            return (double) mergedNum[k/2];

        return (double) (mergedNum[k/2] + mergedNum[k/2 - 1]) / 2;
    }
}*/

/* How can we improve. First thing that comes to mind is since we have both sorted arrays is there a way we can use binary search. Issue is we have 2 different arrays so we have to do binary search in such a way that elements are always sorted. 

  How we can start is that we take the shorter length array and do partition. Once we have the number of elements in nums1, we know how many elements in nums1 are are on left and right. Now for overall binary search partition, we need to make sure the number of elements on the left if both nums1 and nums2 are combined shouls be same as number of elements to the right. Although in case of odd number of total elements, we can have 1 extra on the left. Now to find the number of elements that should be on the left of nums2, it should be total elements/2 - nums1 left elements. 

  example - 
  nums1- 1 3 5 7
  nums2 - 2 4 6 8 10 12

  initial partion on nums1 will be 2 elements on the left. overall number of elements are 10, so it means that the partion on nums2 should be such that the number of elements overall on the left should be 5. So if we already have 2 elements on the left of nums1, we need 3 elements on the left of nums2. 

  So we do partition ans below

  nums1- 1 3 | 5 7
  nums2 - 2 4 6 | 8 10 12

  * Another thing is we are planning to use the array with lesser element for initial partion. If there were 6 elements in nums1 and 4 in nums2, we would have swapped the elements in nums1 and nums2.

 Once we get the initial partition how do we confirm if this is the right one we verify the elements. Technically when the partition is right, the left of num1 at partition should be less than right of partiion of num2. i.e. 3 < 8 which is correct and also the left of num2 should be less than right of num1. Which is not the case here. If it was also right we could have found the partition

 Now 2 cases comes in
 1. if left of num1 > right of num2. In this case, we have to move the partition of num1 to left.
 num1 - 1 8 | 10 12
 num2 - 2 3 4 | 5 6 7

 Why do we need to move the partition to left for num1. Because if we merge the two arrays and keep it sorted the array should be 1 2 3 4 5 6 7 8 10 12. So we are sure that 8 will be on the right and will be greater than the median values so it makes sense to move the partition of num1 to left and based on that move the partition of num2 ro right
 2. if left of num2 > right of num1. In this case, we have to move the partition of num1 to right.

 Another scenario could be what if when we do a partition and we have no element in left of partition of num1. How do we compare to verify if the partition is correct. We cannot compare the null value with any number. What we can do is make the value as Integer.min_value to the left.Similarly of the element on the right is null we can add Integer.max_value

 Why?
 Because when we are checking, we check if the value to the left is smaller to the value to the right. So it makes sense to make the value to the left as min_value and vice versa.

Time - O(log(min(m,n))) because we are performing binary search on smaller array
Space - O(1) 
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if(nums2.length < nums1.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        int start = 0;
        int end = m;
        while(start <= end){
            int partitionX = (start + end) / 2;
            int partitionY = (m + n +1)/2 - partitionX;

            int nums1Left = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int nums1Right = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int nums2Left = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int nums2Right = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if(nums1Left <= nums2Right && nums2Left <= nums1Right){
                if((m + n) % 2 == 1)
                    return (double) Math.max(nums1Left, nums2Left);
                else
                    return (double) (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2;
            } else if(nums1Left > nums2Right)
                end = partitionX - 1;
            else
                start = partitionX + 1;
        }

        return 0.0;
    }
}

