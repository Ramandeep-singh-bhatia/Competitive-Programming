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