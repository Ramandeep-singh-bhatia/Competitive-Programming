class Solution {
    public int strStr(String haystack, String needle) {
        int n = needle.length();
        int m = haystack.length();
        for(int i = 0; i < m - n + 1; i++){
            if(needle.equals(haystack.substring(i, i+ n)))
                return i;
        }
        return -1;
    }
}