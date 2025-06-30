/*
    Approach 1 - Create a list of all the numbers less than n in lexicographical order and return k-1 th number
    Time - O(n) - will cause MLE
*/
/*class Solution {
    public int findKthNumber(int n, int k) {
        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            helper(i, n, result);
        }

        return result.get(k-1);
    }

    private void helper(int i, int n, List<Integer> result){
        if(i > n)
            return;

        result.add(i);
        for(int j = 0; j <= 9; j++){
            int newNum = i * 10 + j;
            if(newNum > n)
                return;
            helper(newNum, n, result);
        }

    }
}*/

/*Approach 1 - Instead of Creating a list of all the numbers less than n in lexicographical order and return k-1 th number, we can keep track of k. Everytime we find a valid number that is less than n and is in lexicographical order, we recursively call the helper until we get the kth element
    Time - O(n) - will cause TLE
    */

/*class Solution {
    int result = 0;
    int count = 0;
    public int findKthNumber(int n, int k) {
        for(int i = 1; i <= 9; i++){
            result = helper(i, n, k);
            if( result != -1 )
                return result;
        }

        return -1;
    }

    private int helper(int i, int n, int k){
        
        if(i > n)
            return -1;
        count++;
        if(count == k){
            return i;
        }
            
        for(int j = 0; j <= 9; j++){
            int newNum = i * 10 + j;
            if(newNum > n)
                break;
            return helper(newNum, n, k);
        }
        return -1;
    }
}*/

/*
    Approach 3 - For the above 2 approaches, we were getting MLE for trying to save all the numbers in a list and returning k-1 th number and TLE for the approach where we only go to the k-1 th number and do not store the whole list. If O(n) is not working, we have to find a way to do it in O(log n)
    For log n, instead of trying to loop to all the numbers till n until we find k-1 th number, we should be skipping the numbers. we can try to find the number of elements taht are lexicographically smaller than n between 2 numbers and see if that number is less than k. I f yes we know that our answer is within the range, else we can skip that whole range and try to find the number in next range. This way we are skipping large set of numbers.
    Example - n = 22 and k = 14.
    In Lexicographical order the numbers would be 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 21, 22 and the 14th number will be 21.
    if we try to find the numbers between 1 and 2, it will be 10 - 19 i.e. 10 numbers that will be smaller than n. We know that including 1 there would be total 11 numbers and we need 14th number so our answer will not be in this range so we can skip the whole set. We loop until k > 0 and we count the number between 1 and 2. including 1 it would be 11. we can then skip those 11 numbers by moving the curr to 2 and reducing 11 from k. Now k is 3 and we have to find 3rd number between 2 and 3. We do this until k = 0.
    Time - O(log n * log n). Log n for outer while loop and log n for helper class
    Space - O(1)
*/

class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while(k > 0){
            int count = helper(curr, curr+1, n);
            if( count <= k){
                curr = curr + 1;
                k -= count;
            } else {
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    private int helper(long curr, long next, int n){
        int count = 0;
        while(curr <= n){
            count += (Math.min(n+1, next) - curr);
            curr *= 10;
            next *= 10;
        }

        return count;
    }
}