/*
    First while loop will run N/2 times because fo two pointer moving towards each other in each iteration. 
    If in case there is a mismatch which can happen only once, the isPalindrome is called twice. It cannot be called more time because only one mismatch is allowed.
    isPalindrome function will run N/2 times because of two pointers making it overall O(N)
    Time - O(N)
    Space O(1)
*/

class Solution {
    public boolean validPalindrome(String s) {
       int i = 0; 
       int j = s.length()-1;

       while (i<j){
           if(s.charAt(i) != s.charAt(j)){
               return isPalindrome(s, i+1 , j) || isPalindrome(s, i , j-1);
           }

           i++;
           j--;
       }

       return true;
    }

    public boolean isPalindrome(String s, int i, int j){
        while(i<j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            
            i++;
            j--;
        }

        return true;
    }
}