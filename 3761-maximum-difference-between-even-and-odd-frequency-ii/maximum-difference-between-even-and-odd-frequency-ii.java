/* Brute force
    Time - O(n^2)
    Space - O(1)
*/

/*class Solution {
    public int maxDifference(String s, int k) {
        
        int n = s.length();
        int result = Integer.MIN_VALUE;
        for(int i = 0; i <= n - k; i++){
            int[] freq = new int[5];

            for(int j = i; j < n; j++){
                freq[s.charAt(j) - '0']++; 

                if(j - i + 1 >= k){
                    result = Math.max(result, getMaxDifference(freq));
                }
            }
            
        }

        return result == Integer.MIN_VALUE ? 0 : result;
    }

    private int getMaxDifference(int[] freq){
        int maxOdd = Integer.MIN_VALUE;
        int minEven = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++){
            if(freq[i] > 0 && freq[i] % 2 == 0){
                minEven = Math.min(minEven, freq[i]);
            } else {
                maxOdd = Math.max(maxOdd, freq[i]);
            }
        }

        if(maxOdd == Integer.MIN_VALUE || minEven == Integer.MAX_VALUE)
            return Integer.MIN_VALUE;

        return maxOdd - minEven;
    }
}*/

class Solution {
    public int maxDifference(String s, int k) {
        int n = s.length();
        int result = Integer.MIN_VALUE;
        for(int a = 0; a <= 4; a++){
            for(int b = 0; b <= 4; b++){
                if( a == b)
                    continue;
                // parity[state] stores the minimum (prev_a - prev_b) for a prefix
                // with the parity state. Initialize with a large value.
                int[] parity = new int[4];
                Arrays.fill(parity, Integer.MAX_VALUE);
                // countARight, countBRight - Prefix counts for the 'right' pointer (s[0...right]).
                // countALeft, countBLeft - Prefix counts for the 'left' pointer (s[0...left]).
                int countALeft = 0, countBLeft = 0, countARight = 0, countBRight = 0;
                int left = -1;

                for(int right = 0; right < n; right++){
                    countARight += (s.charAt(right) - '0' == a) ? 1 : 0;
                    countBRight += (s.charAt(right) - '0' == b) ? 1 : 0;
                    // This loop updates the 'parity' array. It advances the 'left' pointer
                    // and records the state of the prefix ending at 'left'.
                    // The conditions ensure that any substring starting at 'left + 1'
                    // will have a length of at least 'k'.
                    // Also, `countBRight - countBLeft >= 2` since b appears an even number of times in the 
                    // substring, but zero occurrences must be excluded (and 1 must also be excluded since
                    // it's odd, obviously)
                    while(right - left >= k && countBRight - countBLeft >= 2){
                        // Get the parity state for the prefix ending at 'left'.
                        int index = getPartyState(countALeft, countBLeft);
                        // Update the 'best' array with the minimum value of (countALeft - countBLeft)
                        // for this specific state.
                        parity[index] = Math.min(parity[index] , countALeft - countBLeft);

                        left++;
                        countALeft += (s.charAt(left) - '0' == a) ? 1 : 0;
                        countBLeft += (s.charAt(left) - '0' == b) ? 1 : 0;
                    }
                    // Now, calculate the potential answer for the current 'right' pointer.
                    // 1. Get the parity state for the prefix ending at 'right'.
                    int indexRight = getPartyState(countARight, countBRight);
                    // 2. Determine the required state for the start-prefix.
                    // We need `requiredIndexRight = indexRight XOR 10` (binary).
                    int requiredIndexRight = indexRight ^ 0b10;

                    if(parity[requiredIndexRight] != Integer.MAX_VALUE){
                        // Calculate the difference: (countARight-countBRight) - min(countALeft-countBLeft).
                        // This maximizes the expression.
                        result = Math.max(result, countARight - countBRight - parity[requiredIndexRight]);
                    }

                }
            }
        }
        return result;
    }
    /**
     * Helper function to calculate the 2-bit parity state.
     * Bit 1: parity of count A. Bit 0: parity of Count B.
     */
    private int getPartyState(int countA, int countB){
        // (countA & 1) is 1 if countA is odd, 0 if even.
        // << 1 shifts it to the second bit position.
        // | (countB & 1) puts the parity of countB in the first bit position.
        return ((countA & 1) << 1) | (countB & 1);
    }
}

