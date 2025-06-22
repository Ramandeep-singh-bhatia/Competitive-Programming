public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            res += n & 1;
            n = n >>> 1;
            if(i<31)
                res = res << 1;
        }
        return res;
    }
}

/*public class Solution {
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
}*/