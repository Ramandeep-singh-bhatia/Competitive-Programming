/*
    The question is asking to find if any permutation of string s1 is a substring of s2 meaning any combination of s1 characters is a substring in S2 we return true.
    Example - s1 = "ab" and s2 = "bcba". We have ab whose permutations can be ab and ba and we have a ba in s2 so we return true.
    So what we can do is we can keep track of the characters in the string using array of size 26. We can keep track of the frequency of characters using arrays s1Char and s2Char. We loop through s1 and update the frequnce of characters in s1Char. Next we can loop through s2. We can go upto s2.length - s21.length. and for each iteration we check the next number of eleemnts in s2 whose length is equal to s1.length. We do that so that we can compare the frequency of characters for the same length of both s1 and s2. If we do not match the the chracters frequency we know that the characters in the same length of s1 ans s2 are different so we move to the next characters.
    Example  s1 = "ab" and s2 = "bcba". s1 is of length 2. So we start from 0th index and check for length 2 which will be bc. We do not find a match so we move to the next characters of length 2 which is cb. Again we do not have a match. So we move to the next 2 which is ba. this time we found a match so we return true. Else we return false in the end.
    Time complexity - O(l1 + (l2 - l1)* (26 + l1))
    Space complexity - O(1)
*/

/*class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] s1Char = new int[26];
        
        for(int i = 0; i < s1.length(); i++){
            s1Char[s1.charAt(i) - 'a']++;
        }
        for(int i = 0; i <= s2.length() - s1.length(); i++){
            int[] s2Char = new int[26];
            for(int j = 0; j < s1.length(); j++){
                s2Char[s2.charAt(i + j) - 'a']++;
            }
            if(getMatch(s1Char, s2Char))
                    return true;
        }
        return false;
    }

    private boolean getMatch(int[] s1Char, int[] s2Char){
        for(int i = 0; i < 26; i++){
            if(s1Char[i] != s2Char[i])
                return false;
        }
        return true;
    }
}*/

/*
    In above solution we check for the first set of characters of length equal to s1 length in s2. Next we move the the next element and check for the characters of length s1 again in s2. Imagine the length of s1 is 5 and s2 is 10. We check for first 5 elements in s2. Next we move to the 2nd character and gai check for 5 elements. But in this set of characters we have already checked the 4 out of 5 characters and it does not make sense to recheck it. Insetad of checking all the characters again we can do sliding window which will save the time complexity. Since we won't need a loop we can save on the time complexity which will not be 
    O(l1 + (l2 - l1)* 26)
*/
/*class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length())
            return false;
        int[] s1Char = new int[26];
        int[] s2Char = new int[26];
        for(int i = 0; i < s1.length(); i++){
            s1Char[s1.charAt(i) - 'a']++;
            s2Char[s2.charAt(i) - 'a']++;
        }
        if(getMatch(s1Char,s2Char))
            return true;

        for(int i = 0; i < s2.length() - s1.length(); i++){
            s2Char[s2.charAt(i + s1.length()) - 'a']++;
            s2Char[s2.charAt(i) - 'a']--;
            if(getMatch(s1Char,s2Char))
                return true;
        }
        return false;
        
    }

    private boolean getMatch(int[] s1Char, int[] s2Char){
        for(int i = 0; i < 26; i++){
            if(s1Char[i] != 0 && s1Char[i] != s2Char[i])
                return false;
        }
        return true;
    }
}*/

/*
    In the above code for every sliding window we loop through the whole array of size to find if the characters match. Every time a new sliding window comes up we check for all 26 characters. If we can keep track of the number of matches in a window, Next time when we move the window, we can update the number of counts of matching characters by just checking the new incoming character and the one we are removing from the window.
    How do we do that?. We keep track of the number of matching chaacter after we have looped through the s1 and s2 will s1.length meaning we have our first window. We then check for all the characters to find the matching characters and maintain it in a variable.
    Leta take an example s1 = abc and s2 = bbbca
    In 1st iteration the s1Char and s2Char arrays will look like below
    s1Char = 1,1,1,0,0.....0 
    s2Char = 0,3,0,0,0.....0
    There would be 3 mismatch which will be the first 3 chars. hence the num value will be 23.
    If characters in both s1 and s2 for a window match perfectly the num value will be 26. How. lets say if we have s1 as abc ans s2 as bca. After the count we will have s1 - 1,1,1,0,0...0 and s2 - 1,1,1,0,0,...0. making th ematched characters = 26. So we can have a check saying that if num == 26 we can return true.
    Next we slide the window. When we slide, we add a character in from the right of the current end index in s2 and remove a character from the left of the current start index in s2. How we should handle the num. Lets see in the above example. Now the current window will be s2 - bbc. W eare adding a c from the right and removing a b from the left to slide the window. At this point 
    s1Char = 1,1,1,0,0.....0 
    s2Char = 0,2,1,0,0.....0
    If you see there are few changes in the character matching but instead of looping we can update the number of matching characters. first when we add to the right, we check if the character which we have added not tot he window does the frequency matches with s1. If it does we increment num. What if it does not match. There could be a scenario that it was previously matching but since we have added the character it is not not matching. In that case we have to reduce num value as it was previously matching and we had a match added but now it is not matching so we have to reduce the number of match.  So we check if the current character count in s2 where we have added the character is equal to 1 + the s1 character count. It is because we are adding the character count in s2, and in case the count is not matching, by checking if the s2 character count == s1character cunt + 1 we know that it was previously matching so we decrement the num value as now it is not matching.
    Similarly when we are removing the character from the left, we check now if there isa  match in the frequency. If yes we increment  num. If there ia not a match, we check if there was previously a match. since we are reducing the character in s2 cfrequency array, we check if the current value is equal to s1 count - 1. if yes that means there was a match but since we have removed a character from s2 frequency array there is now a mismatch so we reduce the num.
    O(l1 + (l2 - l1))
*/

/*class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length())
            return false;
        int[] s1Char = new int[26];
        int[] s2Char = new int[26];
        for(int i = 0; i < s1.length(); i++){
            s1Char[s1.charAt(i) - 'a']++;
            s2Char[s2.charAt(i) - 'a']++;
        }
        int num = 0;
        for(int i = 0; i < 26; i++){
            if(s1Char[i] == s2Char[i])
                num++;
        }
        

        for(int i = 0; i < s2.length() - s1.length(); i++){
            int right = s2.charAt(i + s1.length()) - 'a';
            int left = s2.charAt(i) - 'a';
            if(num == 26)
                return true;
            s2Char[right]++;
            
            if(s2Char[right] == s1Char[right])
                num++;
            else if(s2Char[right] == s1Char[right] + 1)
                num--;
            s2Char[left]--;
            if(s2Char[left] == s1Char[left])
                num++;
            else if(s2Char[left] == s1Char[left] - 1)
                num--;
            
        }
        return num == 26;
    }
}*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if(m > n)
            return false;
        int[] s1count = new int[26];
        int[] s2count = new int[26];

        for(int i = 0; i < m; i++){
            s1count[s1.charAt(i) - 'a']++;
        }

        int left = 0, right = 0;

        for(; right < n; right++){
            s2count[s2.charAt(right) - 'a']++;

            if(right - left + 1 > m){
                s2count[s2.charAt(left) - 'a']--;
                left++;
            }

            if(Arrays.equals(s1count, s2count)){
                return true;
            }
        }

        return false;
    }
}