/*
 Maintain an array that will store the length of the longest increasing subsequence that end at that element i.e. for a given array [0,1,0,3,2,3], the array at index 4 will store the longest increasing subsequence length that ends with element in index 4 which is 2. 

How do we do that is at that given index we will check the element at the index with all the element on the left of the element and compare if it is greater than the element. If yes then we can say that this given element can attach at the end of the eleemnt which is smaller than the given element and still be the longest increasing subsequence. Then we take the max of dp value of the elements + 1 for
the given element.

For example array [0,1,0,3,2,3] - 
element 0 - 1
element 1 - 2
eleemnt 0 - 1, because we are looking for strictly increaing subsequence
element 3 - 3, It can ataach itself behind all 3 elements on the left but to form the longest strictly increaing subsequence it shoudl attach behind 1 and form the subsequence 0 - 1 - 3, which means we will have to take the max of all the element to the left so we can take the max + 1
element 2 - 3 , It can ataach behind - 0, 1 and 0 and the max is 2 for element 1
element 3 - 4 - It can attach behind - 0,1,0,2 and max is 3
*/

/*class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        int result = 0;
        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j])
                    max = Math.max(max, lis[j]);
            }
            lis[i] = max + 1;
            result = Math.max(result , lis[i]);
        }

        return result;
    }
}*/

/*
    In the above approach we maintain a array with the larget increasing subsequence length till that element. This apporach required us to loop through each element starting from 0 till that eleemnt to find the number of elements which are smaller than the current number and keep track of that in an array. In the end we can return the maximum value from that array. Another approach is to maintain the longest subsequence list If we find a number which is greater than the last number in the list, we can automatically add that number to the list. If not, we loop through the list and find the number which is larger than the current number and replace it with the number. Lest say we have first number as 6 and next we get 1, we know that we have a chance to get some numbers betweene 1 and 6 in the future, so it makes sense to maintain the list with the smaller number in the begning as we need to find the maximum length of incresing subsequence. This will still be O(n^2) but we can imporve on this if we do a binary search as the list we are maintaining will be sorted and we can easily do binary search to find the number that is just larger than the current number so that we cna replace it in the list with the current number.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lis.get(lis.size() - 1)) {
                lis.add(nums[i]);
            } else {
                // Find the first element in sub that is greater than or equal to num
                int j = 0;
                while (nums[i] > lis.get(j)) {
                    j++;
                }

                lis.set(j, nums[i]);
            }
        }
        
        return lis.size();
    }
}

/*class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > lis.get(lis.size() - 1))
                lis.add(nums[i]);
            else{
                int j = binarySearch(lis, nums[i]);
                lis.set(j,nums[i]);
            }
            
            
        }
        return lis.size();
    }

    private int binarySearch(List<Integer> lis, int num){
        int left = 0;
        int right = lis.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(num == lis.get(mid))
                return mid;
            if(num < lis.get(mid)){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}*/