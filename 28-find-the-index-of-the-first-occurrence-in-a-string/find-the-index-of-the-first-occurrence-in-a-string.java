/*class Solution {
    public int strStr(String haystack, String needle) {
        int n = needle.length();
        int m = haystack.length();
        for(int i = 0; i < m - n + 1; i++){
            if(needle.equals(haystack.substring(i, i+ n)))
                return i;
        }
        return -1;
    }
}*/

class Solution {
    public int strStr(String haystack, String needle) {
        int n = needle.length();
        int m = haystack.length();
        for(int i = 0; i < m - n + 1; i++){
            for(int j = 0; j < n; j++){
                if(haystack.charAt(i + j) != needle.charAt(j))
                    break;
                if(j == n-1)
                    return i;
            }
        }

        return -1;
    }
}