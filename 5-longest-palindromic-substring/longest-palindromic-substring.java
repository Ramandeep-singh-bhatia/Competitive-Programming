class Solution {
    int left = 0;
    int right = 0;
    public String longestPalindrome(String s) {
        String result = null;
        int maxLength = Integer.MIN_VALUE;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(isPalindrome(s,i,j)){
                    if(maxLength < j - i){
                        maxLength = j - i;
                        result = s.substring(i, j+1);
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s, int start, int end){
        while(start <= end){
            if(s.charAt(start) != s.charAt(end))
                return false;
            else{
                start++;
                end--;
            }
        }

        return true;
    }
}