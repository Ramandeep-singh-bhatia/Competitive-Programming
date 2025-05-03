/*
    We have an envelope with width and height. We have to find the maximum number envelopes that can go one into the other given that the envelope can go into other envelop if both the width and height is less than the other. In a way we have to find the envelopes such that both the width and height is less than the other envelope then only we can merge them into one. We have to find all such elements. In a way we can use the concept of longest increasing subsequence but we have to make sue both width and height are greater than the previous one. 
    First thing I can think of is sorting the envelopes array using width. Once sorted we know that the width is increasing order and all we have find now is how many among those form a LIS using height. This way we can find the number. If the width is same, we can sort using height. Next we can implement LIS for the height.
    For LIS we can traverse through the 2D array and at that given index we will check the element at the index with all the element on the left of the element and compare if it is greater than the element. If yes then we can say that this given element can attach at the end of the eleemnt which is smaller than the given element and still be the longest increasing subsequence. Then we take the max of lis value of the elements + 1 for the given element.

For example array [0,1,0,3,2,3] - 
element 0 - 1
element 1 - 2
eleemnt 0 - 1, because we are looking for strictly increaing subsequence
element 3 - 3, It can ataach itself behind all 3 elements on the left but to form the longest strictly increaing subsequence it shoudl attach behind 1 and form the subsequence 0 - 1 - 3, which means we will have to take the max of all the element to the left so we can take the max + 1
element 2 - 3 , It can ataach behind - 0, 1 and 0 and the max is 2 for element 1
element 3 - 4 - It can attach behind - 0,1,0,2 and max is 3

    Time complexity - O(nlogn + n^2) nlogn for sorting and n^2 for LIS
    Space complexity - O(n) - for dp array

    Simple explaination - 
    We can sort using width. We have to find the LIS for the 2D array meaning both the width and the height should be greater than the previous element to be part of LIS. 
    We can maintain an array that keep track of LIS till that element. 
    For any element, we have to loop and find the maximum LIS value of the previous elements and then add 1 to it, meaning this element can be attached at the end of the LIS and increase the overall LIS. We keep check of the element by verifying if both width and height is greater then only it should be included in LIS. 
    In the end the result will be the maximum among the LIS formed at each element.
*/

/*class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> a[0] - b[0]);
        return helper(envelopes);
    }

    private int helper(int[][] envelopes){
        int n = envelopes.length;
        int[] LIS = new int[n];
        int result = 0;
        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < i; j++){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                    max = Math.max(max, LIS[j]);
            }
            LIS[i] = max + 1;
            result = Math.max(result, LIS[i]);
        }

        return result;
    }
}*/

/*
    The above solution will be TLE because of large array length. We can try improve on the time complexity from n^2. In the above approach we maintain a array with the larget increasing subsequence length till that element. This apporach required us to loop through each element starting from 0 till that eleemnt to find the number of elements which are smaller than the current number and keep track of that in an array. In the end we can return the maximum value from that array. Another approach is to maintain a list where l[i] represents the smallest possible value that can end an increasing subsequence of length i+1.
    We are sorting the 2D array using width in increasing order. What happens when we get same width, If we try to sort the array for same width in increasing order and do LIS using height, we might end up getting unecessary elements that become eligible for LIS but it will give wrong result.
    envelope - [[2,3], [5,4], [6,5], [6,7], [2,2]]
    After sorting both increasing order - [[2,2], [2,3], [5,4], [6,5], [6,7]]
    LIST using just height will be. 2,3,4,5,7 but this is wrong as 2,2 and 2,3 cannot be part of the LIS as width is same. Similarly 6,5 and 6,7 cannot be part of the list too.
    One way to avoid this problem is to sort the 2d array with width in ascending and height in decending order
    After sorting - [[2,3], [2,2], [5,4], [6,7], [6,5]]
    When we get 2,3 it can be part of the LIS.and when we reach 2,2, we can skip it. This will form LIS using height - 3,4,7 and If you look at the dimentions, it should be able to fit the envelopes as the dimentions are as per the problem rule which is 2,3 - 5,4 - 6,7

    But what if after sorting we have below scenario
    After sorting - [[2,6], [2,1], [5,4], [6,7], [6,5]]
    In this case the envelopes that we can fit will be 2,6 - 6,7 but technically if we use 2,1 we can fit in 5,4 and then 6,7 or 6,5 making LIS to 3.
    This means if we have same width and we are sorting height in decending order, when we get a height smaller than the previous height, we can include that envelope so that it lets us fit more envelopes
    
    If we find a number which is greater than the last number in the list, we can automatically add that number to the list. If not, we loop through the list and find the number which is larger than the current number and replace it with the number. Why this will work is lets say we have the list [2,7,8] and we get a 5. Obviously 5 is smaller than 8 and will not contribute to the LIS. Next we get a 6 and it will still not contribute to LIS. But if we replaced 7 by 5, though it will not change the length of LIS but it give more options to fit in number sand eventually increasing the LIS length i.e. when we get 5 we canc ahneg the list to [2,5,8] and when we get 6, the LIST will be [2,5,6,8] This list will
    
     This will still be O(n^2) but we can imporve on this if we do a binary search as the list we are maintaining will be sorted and we can easily do binary search to find the number that is just larger than the current number so that we can replace it in the list with the current number.

    Time complexity - O(nlogn + n^2)
    Space complexity - O(n)
*/

/*class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> {
            if(a[0] == b[0])
                return b[1] - a[1];
            else
                return a[0] - b[0];
        });
        return helper(envelopes);
    }

    private int helper(int[][] envelopes){
        List<Integer> l = new ArrayList<>();
        int n = envelopes.length;
        l.add(envelopes[0][1]);
        for(int i = 1; i < n; i++){
            if(envelopes[i][1] > l.get(l.size() - 1))
                l.add(envelopes[i][1]);
            else {
                int j = 0;
                while(envelopes[i][1] > l.get(j))
                    j++;
                l.set(j, envelopes[i][1]);
            }
        }
        return l.size();
    }
}*/

/*
    Use binary search to reduce the time complexity from O(n^2) to O(nlogn)
*/

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> {
            if(a[0] == b[0])
                return b[1] - a[1];
            else
                return a[0] - b[0];
        });
        return helper(envelopes);
    }

    private int helper(int[][] envelopes){
        List<Integer> l = new ArrayList<>();
        int n = envelopes.length;
        l.add(envelopes[0][1]);
        for(int i = 1; i < n; i++){
            if(envelopes[i][1] > l.get(l.size() - 1))
                l.add(envelopes[i][1]);
            else {
                int j = binarySearch(l, envelopes[i][1]);
                l.set(j, envelopes[i][1]);
            }
        }
        return l.size();
    }

    private int binarySearch(List<Integer> l, int num){
        int left = 0;
        int right = l.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(num == l.get(mid))
                return mid;
            if(num < l.get(mid)){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left; // Since we have to find the number that is just greater than num, we return left because else condition will only be satisfied if num > l.get(mid)
    }
}