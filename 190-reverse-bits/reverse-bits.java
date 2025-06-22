public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0; 
        int position = 31;
        while(n != 0) {
            result += (n & 1) << position;
            n = n >>> 1;
            position -= 1;
        }

        return result;
    }
}