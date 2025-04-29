/*
 Memoization code:

    We need to find the number of palindromic substrings. If we want to write memoization code we will need to use recursion but store the recursion values in a 2d array to avoid recomputing and changing the exponential time complexity O(2^n) to O(n^2).

    To find number of palindromic substring we can try to find if each of the substring is palindromic or not. How do we do that. We will have a 2D dp array and store true for i==j. Why? Because if i==j it means that we have same character and also 1 character is always a plaindrome.

    Next if we have a palindrom we check i+1, j-1 is palindrome or not. If it is we return true and inncrement result to get the count.
*/

/*class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = i; j< n; j++){
                if(i == j)
                    dp[i][j] = true;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = i; j< n; j++){
                if(isPalindrome(s,i,j,dp))
                    result++;
            }
        }

        return result;
    }

    private boolean isPalindrome(String s, int i, int j, boolean[][] dp){
        if(i >= j)
            return true;
        if(dp[i][j])
            return dp[i][j];
        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] = isPalindrome(s,i+1, j-1, dp);
        }

        return false;
    }
}*/

/*
    For bottom up approach we have boolean[][] dp which keep s track of palindromic substring . If we are checing for i and j and the characters are equal, we will have to check the subproblem i+1 and j-1. If it is palindromic we know that we foudn anew palindrome and we can incrmeent the result. 

    How do we implement it. We have a length loop that indicates the length of substring between iand j. len = 1 means there is 1 character in the substring. len can be from 1 to n where n will have the whole substring.

    For each length we try to get the i which is the start of the substring and will go upto n - len making sure we are with in the limit of the length

    For j we will calculate using i and length to make sure we check the extreme values of what ever length we are checiking. We start from the extreme end of the substring and then see if the characters are equal or not. If they are equal we will check the i+1 and j-1 if it is a palindrome or not. If it is we know that we found a new palindrome and we increment the result. 

    first condition is. i ==j meaning there is only one character which will always be a palindrome. next we check if the characters at i and j are equal. If yes we have two conditions. One is that we are only checking len =2. in that case we know taht it is palindrome and we don't have to check for other characters. other wise we check for i+1 and j-1. if they are plaindrome. In both cases we update dp with true and increment the result.
*/

/*class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = 0;

        for(int len = 1; len <= n; len++){
            for(int i = 0 ; i <= n - len; i++){
                int j = i + len - 1;
                if(i == j){
                    dp[i][j] = true;
                    result++;
                }
                else if(s.charAt(i) == s.charAt(j)){
                    if( len == 2 || dp[i+1][j-1]){
                        dp[i][j] = true;
                        result++;
                    }   
                }     
            }
        }
        return result;
    }
}*/

/*
    Most efficient approach will be time complexity O(n^2) and space complexity O(1) where we expand around the middle.

    Intuition: 
    
    what we usually do in a memoization/recursion approach is that we check for all possible substring and check the characters at the extreme ends. If they are equal we contract the substring by i+1 and j-1 to compare the characters until we reach to the same character or i > j. So we are moving inward and checking for palindrome. We keep doing it for all the possible substrings one by 1 and storing the boolean in 2d array. We have to store the result of whether a substring is a palindrome or not so that in case we encounter the same substring again we can get the result directly from the dp array. 
    We can save space with expand around the middle approach. Lets talk about the example Example - aaab - Palindromic substring would be a,a,a,b, aa, aa,aaa so 7.

    Lets start with first a. It is a palindrome so we increment result. Next we expand on both sides. Left will be out of bound so we get result = 1.
    Next we move to 2nd a. It is also a palindrome so we increment result. Next we expand and move left and right. we get aaa so we increment result again. Not is is 3. When we expand further, left goes out of bound so we stop. 
    Next we go to 3rd a. It is palindrome so we increment result. Next when we expand we get aab and it is not a palindrome as a != b. So the result is 4 till now.
    Next we move to b. It is a palindrome so we increment result. Next when we expand we go out of bounds for right. At this point result is 5.
    If we notice the answer should have been 7 but we got 5. Why?. Beacuse the palindromeic substring we got here is a,a,a,b, aaa. All are odd numbers. We actually missed aa and aa which are even.
    So we will have to also tackle the even number palindromeic substring.
    We start with first 2 aa. We foudn a palindrome so we add 1 to the result we then expand and went out of bound so we stop.
    Next we start with 2nd a and take aa. We found a palindrome and increment result. Next we expand to aaab. a and b are not equal so we stop. and we get the result as 7. 
*/

class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            result += getSubstring(s, i, i);
            result += getSubstring(s,i,i+1);
        }

        return result;
    }

    private int getSubstring(String s, int left, int right){
        int result = 0;
        while(left >= 0 && right <= s.length() - 1){
            if(s.charAt(left) != s.charAt(right))
                break;
            left--;
            right++;
            result++;
        }

        return result;
    }
}
