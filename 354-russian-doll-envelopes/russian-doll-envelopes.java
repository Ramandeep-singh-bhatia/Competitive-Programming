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
    The above solution will be TLE because of large array length. We can try improve on the time complexity from n^2. In the above approach we maintain a array with the larget increasing subsequence length till that element. This apporach required us to loop through each element starting from 0 till that eleemnt to find the number of elements which are smaller than the current number and keep track of that in an array. In the end we can return the maximum value from that array. Another approach is to maintain the longest subsequence list If we find a number which is greater than the last number in the list, we can automatically add that number to the list. If not, we loop through the list and find the number which is larger than the current number and replace it with the number. Lest say we have first number as 6 and next we get 1, we know that we have a chance to get some numbers betweene 1 and 6 in the future, so it makes sense to maintain the list with the smaller number in the begning as we need to find the maximum length of incresing subsequence. This will still be O(n^2) but we can imporve on this if we do a binary search as the list we are maintaining will be sorted and we can easily do binary search to find the number that is just larger than the current number so that we cna replace it in the list with the current number.

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