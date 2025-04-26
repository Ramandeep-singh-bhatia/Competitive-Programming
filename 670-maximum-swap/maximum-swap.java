/*
    This solution uses a greedy approach to find the maximum possible number by swapping at most once. Let's break down the steps:

Convert the given integer num to a character array arr to easily manipulate individual digits.

Create an array rightIndex of size 10 to store the rightmost index of each digit in the given number. This array is used to quickly find the rightmost occurrence of a digit when traversing the number from left to right.

Iterate through the character array arr and populate the rightIndex array with the rightmost index of each digit encountered.

Now, iterate through the character array again. For each digit, consider swapping it with a larger digit (going from 9 to arr[i] - '0' + 1). If a larger digit is found at a right index greater than the current index (rightIndex[j] > i), perform the swap and return the result.

If no swap is performed during the iteration, return the original number because no better swap is possible.
Time - O(n) Overall
       O(n) for converting integer to array
       O(n) for looping through the array to populate rightmostindex
       O(n) for outer loop to swap the digit
       O(n) for converting the string back to integer
Space - O(n) 
*/

class Solution {
    public int maximumSwap(int num) {
        char[] arr = String.valueOf(num).toCharArray();

        int[] rightMostIndex = new int[10];

        for(int i = 0; i < arr.length; i++){
            rightMostIndex[arr[i] - '0'] = i;
        }

        for(int i = 0; i < arr.length; i++){
            for (int j = 9; j > arr[i] - '0'; j--){
                if(rightMostIndex[j] > i){
                    char temp = arr[rightMostIndex[j]];
                    arr[rightMostIndex[j]] = arr[i];
                    arr[i] = temp;
                    return Integer.valueOf(new String(arr));
                }
            }
        }

        return num;
        
    }
}