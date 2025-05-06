class Solution {
    public int myAtoi(String s) {
        int result = 0;
        int index = 0;
        int n = s.length();
        boolean isNegative = false;

        // Discard all spaces from the beginning of the s string.
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && s.charAt(index) == '+') {
            index++;
        } else if (index < n && s.charAt(index) == '-') {
            isNegative = true;
            index++;
        }

        // Traverse next digits of s and stop if it is not a digit
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if (
                (result > Integer.MAX_VALUE / 10) ||
                (result == Integer.MAX_VALUE / 10 &&
                    digit > Integer.MAX_VALUE % 10)
            ) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return isNegative ? -result : result;
    }
}