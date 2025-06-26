/*
    For a given number n we can either add or subtract the power of 2 from the number. We can randomly try to add or subtract the power of 2 and try to get the result but it does nto makes sense to try everything. We need some pattern. Lets break inot addition and subtraction.
    Subtraction - Lets say we have a number 39 - 100111. If I want to subtract a number all we have to do is remove the leftmost bit. In this case it is 2^5 i.e 32. This will remove one set bit This way we can reduce the number to 0 which is the goal.
    Addition - I should be adding a number to n such that it is the next power of 2. This will reduce the number of 1's and make it easier to do 1 operation of subtraction to reduce it to 0.

                                                        39
                            39 - 32 = 7                                   64 - 39 = 25
            7 - 4 = 3                       8 - 7 = 1           25 - 16 = 9                 32 - 25 = 7
3 -2 = 1            4-3 = 1             1-1 = 0          9 - 8 = 1      16 - 9 = 7      7-4=3       8-7=1

In the above example, the left of ach number represents the subtraction operation and right represents the addition operation. We have to subtract 2^5 from 39 to remove the leftmost set bit. For addition we have to add a number so that it becomes the closes power of 2. In above case it is 25. We add 25 (16 + 8 + 1) to reach the closest power of 2. 

Time - O(log^2 n) - Number of unique subproblems: O(log n). Each call to minOperations(n) includes this loop: Work per subproblem: O(log n)
Space - O(log n)
*/

class Solution {
    Map<Integer, Integer> dp = new HashMap<>();
    public int minOperations(int n) {
        if(n == 0 || n == 1){
            dp.put(n,n);
            return n;
        }

        if(dp.containsKey(n))
            return dp.get(n);

        int val = 1;
        while(val < n){
            val *= 2;
        }
                            // Subtraction                Addition
        dp.put(n, 1 + Math.min(minOperations(n - val/2), minOperations(val - n)));
        return dp.get(n);
    }
}