/*
    Let N be the length of nums, which represents the number of integers for which we have to check.
Let M be the average length of the number.
Time - O(N*M) - N because of for loop and M because of while loop is isEven. After each iteration,we reduce the length by 1.
*/

class Solution {

    public int findNumbers(int[] nums) {
        // Counter to count the number of even digit integers
        int result = 0;

        for (int num : nums) {
            if (isEven(num))
                result++;
        }

        return result;
    }

    // Helper function to check if the number of digits is even
    private boolean isEven(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count % 2 == 0;
    }
}