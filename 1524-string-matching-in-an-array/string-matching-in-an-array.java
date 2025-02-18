/*
    if n is the length of words array and m is the length of longest word.
    We loop throught he array twice and for each loop we compare the word with each other. 
    Time complexity - O(n^2*m^2) - N^2 for nested loop and m for iteration in isSubstring method and another m for substring (word2.substring(i, i + word1.length())) making it m^2
    Space complexity - O(1)
*/
/*class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        for(int i = 0; i < n; i++){
            for(int j= 0; j < n; j++){
                if(i != j && isSubString(words[i], words[j])){
                    result.add(words[i]);
                    break;
                }
            }
        }

        return result;
    }

    private boolean isSubString(String word1, String word2){
        boolean isSub = false;
        if(word1.length() > word2.length())
            return isSub;
        for(int i = 0; i < word2.length(); i++){
            if(word1.charAt(0) != word2.charAt(i))
                continue; 
            if(i + word1.length() <= word2.length() && word2.substring(i, i + word1.length()).equals(word1)){
                isSub = true;
                break;
            }
        }

        return isSub;
    }
}*/

/*
    KMP Algo. The kmp algorithm is the regular implementation. How we are using it in the problem is a bit different. For kmp we calculate the LPS(longest prefix suffix) array and use this to find the match in the string. So what we are going to do is check each word and then calculate LPS for the word and treat that as a pattern and check for all the other words if this pattern is the substring of the rest of the words. This will be O (n^2) to compare the words with each other. We can add a condition so that they so not compare with the same word. If we find the match we break the loop. 
    Time complexity - O(n^2 * m) - 
        O(n) - outer loop
        O(m) - LPS calculation
        O(n * m) - Inner loop and for each word we do a string comparision
        Overall - O(n) * [O(m) + O(m) * O(n)] - O(m*n) + O(n^2 * m) - O(n^2 * m)
    Space Complexity - O(m)
        O(m) - LPS array.
        O(n) - result but this is an auxillary space
*/
class Solution {
    public List<String> stringMatching(String[] words) {
        int n = words.length;
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            int[] lps = computeLPS(words[i]);
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                // Check if words[j] is a substring of words[i]
                if(kmp(words[i], words[j], lps)){
                    result.add(words[i]); 
                    break;
                }
            }
        }
        
        return result;
    }

    private int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        
        int j = 0;
        int i = 1;
        
        while(i < m) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                lps[i++] = ++j;
            } else {
                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return lps;
    }

    private boolean kmp(String pattern, String string, int[] lps){
        int m = pattern.length();
        int n = string.length();
        if(n < m) return false;
        
        // Pattern matching
        int i = 0;
        int j = 0;
        while (i < n) {
            if (pattern.charAt(j) == string.charAt(i)) {
                i++;
                j++;
                if (j == m) {
                    return true;  // Found the pattern
                }
            } else {
                if (j != 0) { 
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return false;
    }
}