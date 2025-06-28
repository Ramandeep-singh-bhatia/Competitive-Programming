/*
    BruteForce - Loop through the whole string and check for each index of it forma palindrome or not. If is is a plaindrome we keep track fo the maximum length and return the substring of maxLength and starting index. 
    Time - O(n^3)
    Space - O(1)
*/

/*class Solution {
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
}*/

/*
    To optimize we can reduce the computation of checking the palindrome for each indexes and memoize it. We keep track of the palindrome if computed earlier and instead of looping through again get it from the memoization. 
    Time - O(n^2)
    Space - O(n^2)
*/

class Solution {
    boolean[][] dp;
    public String longestPalindrome(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        int maxLength = Integer.MIN_VALUE;
        int startIndex = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(helper(s, i,j)){
                    if(maxLength < j - i){
                        maxLength = j - i;
                        startIndex = i;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLength + 1);
    }

    private boolean helper(String s, int left, int right){
        if(left >= right)
            return true;
        
        if(dp[left][right])
            return dp[left][right];
        
        if(s.charAt(left) == s.charAt(right))
            return dp[left][right] = helper(s, left + 1, right - 1);

        return dp[left][right] = false;
    }
}

/*
   Expand from the middle. We loop through each character and expand to eh left and right to find the palindrome. If we there is a palindrome we check if the length is greater than the maxLenght. If it is we update the result. 
   When expanding from the middle. there could be 2 scenarios oddlength string aba. When we start from b, we have to expand to the left of ba and to the right of b but the starting pint will be at b, hence we call the helper using (s, i,i)
   Another scenario will be if we have even length palindrom like abba. If we start from b, we will have to exploare from i, i+1. 
   So for each element in the string, we check for both odd and even length palindromes.
   Time - O(n^2)
   Space - O(1)
*/

/*class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLength = Integer.MIN_VALUE;
        String result = null;
        for(int i = 0; i < n; i++){
            String odd = helper(s, i , i);
            if(maxLength < odd.length()){
                maxLength = odd.length();
                result = odd;
            }

            String even = helper(s, i , i+1);
            if(maxLength < even.length()){
                maxLength = even.length();
                result = even;
            }
        }

        return result;
    }

    private String helper(String s, int left, int right){
        if(s.length() <= 1)
            return s;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}*/