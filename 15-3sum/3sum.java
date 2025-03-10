/*
    Since we have both -ve and +ve values in the array, to find the sum of 3 values which is equal to 0, we need few -ve and few positive values. We cannot have all -ve or all +ve value and get a 0. One approach is that we take an element and then use two sum for the other two eleemnt in the array where the target will be -ve of 1st emenet selected. The issue here is if we have all the +ve elements we still will have to pick the first element and check for the rest of the element to find a pair. Then we move to the next element and so on till the end. This is unecessay computation as it is possible that we have no -ve values in the array or just 1 -ve value in the array. If we have sorted array, we know as soon as we reach +ve values we will never find a triplet that sums to 0 and we can skip the computation
    What we can do is start by sorting the array in ascending order. Next we can pick a -ve value and then from the remaining values we can have two pointers, one to the left and one to the right. We can take the sum of these 3 values and see of the sum is == 0. If it is equal we got a triplet, if the sum is less than 0 we know we need to add value to the sum so we move the left pointer. If the sum is greater than 0, we move the right pointer. 
    Edge case 1 - Once we find a triplet, we continue looking for more element in the array to find another triplet using the first element that we have selected.
    Example - [-1,0,1,2,-1,-4]. Sorted [-4, -1, -1, 0, 1, 2]. Using -1 we can get two triplets [[-1,-1,2],[-1,0,1]]
    Edge case 2 - Lets say we have an array which is sorted like -1,-1,0,1
    We take the first element -1 and find a triplet -1,0,1. 
    Next we can move to the next -1 but this will again form a triplet -1,0,1. It is mentioned that we cannot have duplicate triplet, So what we can do is we skip the rest of -1 once we have found a triplet.
*/

/*class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        // We need nums[i] <= 0 condition because of the left most value is ve we will never be able to find a triplet whose sum is 0.
        for(int i = 0 ; i < nums.length && nums[i] <= 0; i++){
            if( i == 0 || nums[i-1] != nums[i]){ // Skip if we have same element next to each other
                twoSum(i, nums);
            }
        }

        return result;
    }

    public void twoSum(int i, int[] nums){
        int left = i + 1, right = nums.length - 1;
        while(left < right){
            int sum = nums[i] + nums[left] + nums[right];
            if(sum < 0)
                left++;
            else if(sum > 0)
                right--;
            else {
                result.add(Arrays.asList(nums[left++] , nums[i], nums[right--]));
                // Once we find a triplet with a element, the left and the right, We can continue finding more triplet by using same elemnt but moving the left pointer
                while(left < right && nums[left] == nums[left - 1]){
                    left++;
                }
            }
        }
    }
}*/

/*
    Follow up - Sorting of the array is not allowed. 
    In a brute force we need 3 loops to check for all combinations. If we want to reduce to 2 loops, we can use hashing. We can start the loop from the left and we check for the rest of the element on the right. For each of the two numbers that we have selected we can check if there is a 3rd number that exist so that the sum is = 0. The third number can be calculated like num1 + num2 + num3 = 0 so num3 = - (num1 + num2). We Not only need a unique triplet, we also need to make sure that we have all 3 numbers at different indexes in the triplet to satisfy the condition i != j, i != k, and j != k.
    What we can do is maintain a hashset so that we can check if there exist a third  number without traversing the elements. So we loop through 0 to n, we have a 2nd loop from i+1 to n. At each combination of nums at i and j we check if the 3rd number exist or not. If it exist we form the triplet and add it to the list. if it does not exist we add the element to the set and traverse through the remaining element.
    To make sure we have unique triplets we can have a set<List<Integer>> and in the end we return it as a list. We also will have to sort the triplets to make sure we only have unique triplets in the set
    Time complexity - Two loops so O(n^2) but we also have a hashset. Hashset .contains usually takes O(1) time to execute contains but in case there are lots of collisions and all the numbers go to a same hash code, in worse case it would have gone to O(n). But in java 8 and higher the hasset implementation uses red black tree where worse case time compleixty is reduced to logn even if there are a lot of collisions.
    Its O(n^2 + logn) which eventually will be o(n^2)
    Space complexity will be because of two sets, one set will be at a time in worse case can hold n-2 elements when i is at 0 and j is at last element. Also lets say there can m number of triplets so it could be O(n + m).
*/

/*class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i){
            set = new HashSet<>();
            for (int j = i + 1; j < nums.length; ++j) {
                int third = - nums[i] - nums[j];
                if(set.contains(third)){
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(triplet);
                    result.add(triplet);
                }
                set.add(nums[j]);
            }
        } 
        return new ArrayList(result);
    }
}*/

/*
    In the above code we have hashset that we are using to keep track of the elemnt between two elements in the loop and checking the third element exist in the hashset or not to forma a triplet. Everytime we move the i pointer, we go and check the rest of the elements again and again. Example
    [-1,0,1,2,-1,-4]
      i        j
      At this point hashset will have 0,1,2
      Next when i is moved [-1,0,1,2,-1,-4]
                               i.     j
    We will be checking for the third element in the hashset and hashset will be reset in each outer loop making it inefficinet.
    For this to run efficiently, we can recall the 2 sum implementation using Hashmap. We had to return the indexes of the elements which has sum equal to the target. We use to traverse through the array and add the indexes of the element. Next we use to get the target - the eleemnt and see if it exist or not. If it does we return the indexes. In a way we were maintaining the indexes of each element and in next loop we check if the complement exist in the map and also it is not same index as the element.
    We can do something similar here. Only difference is we can have a map to store the pair.
    It is similar to hashmap one pass implementation. In the iteration of two for loops we check if the the third element exist in the map and it already has a map with nums[i] indicating that nums[i], nums[j] and third element are the triplets whose sum is zero. If it does not exist we add the combination of nums[i], nums[j] in the map.
    Apart from this we will also need to make sure we keep track of the element we have already used to form a triplet. Lets say in a array we got {-1,-1,0,1} and we form a triplet using first -1 which will be -1,0,1. Now it does not make sense to try to find another triplet using second -1 as it will end up getting same triplet. So for every eleemnt that we have traversed in the outer loop we will only try to create the triplet if it does not exist in the set. If it does not exist we process the triplet and then add the element in the end
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> pair = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (!set.contains(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int third = -nums[i] - nums[j];
                    if (pair.containsKey(third) && pair.get(third) == nums[i]) {
                        List<Integer> triplet = Arrays.asList( nums[i], nums[j], third);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                    pair.put(nums[j], nums[i]);
                }
                set.add(nums[i]);
            }
        }
        return new ArrayList(result);
    }
}