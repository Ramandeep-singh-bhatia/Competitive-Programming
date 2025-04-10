class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
       /* int i =0 ,j = 0;
        int count = 0; // count variable will maintain the number of characters that needed to be skipped in word. In short the integer value in abbr

        // We run a while loop through each string word and abbr until we reach the end of either one of them. abbr will be valid only if both reach the end hence the return is checking the i and j to be equal to respective string length
        while(i < word.length() && j < abbr.length()){
            // Edge cases - if the first element in the integer part of abbr is ) it means that it has leading zero and also replaces an empty string. in both cases its not a valid string. hence return false.
            if(abbr.charAt(j) == '0')
                return false;
            // we check if the character is a lowercase english letter. If the characters does not match that means it is not a valid abbr we return false, else we continue checking each character till we reach to a number.
            if(abbr.charAt(j) >= 'a' && abbr.charAt(j) <= 'z'){
                    if(word.charAt(i) != abbr.charAt(j))
                        return false;
                    else {
                        i++;
                        j++;
                    }
            } else {
                // Else condition is that its a number. Now we have to find the whole integer in abbr.
                // While loop will check if we have reached the end of the abbr or we have reach to a character in the abbr. everyrime we find a number we mutilpy it with 10 and add the number we find. If 1st number is 2 count will be 0 so it will be 0*10 + 2. count will be 2. Next iteration if we find 4 , we will do count * 10 which will be 20 + the number found 4 hence we will get 24. 
                while (j < abbr.length() && !(abbr.charAt(j) >= 'a' && abbr.charAt(j) <= 'z')){
                    count = count * 10 + Character.getNumericValue(abbr.charAt(j));
                    j++;
                }
                // Edge case - the maximum length of the word is 20 as mentioned in constraints, hece if the count goes above 20 it returns false
                if(count > 20)
                    return false;
                // once we find the full number in abbr we will move the index in word by the count so that we can compare the remainig characters
                i += count;
                // After each iteration we will reset the count to 0
                count = 0;
            }
        }
        
        // abbr will be valid only if both reach the end hence the return is checking the i and j to be equal to respective string length
        return (i == word.length() && j == abbr.length());*/

        int i = 0;
        int j = 0;

        int count =0;

        while(i < word.length() && j < abbr.length()){
            if(abbr.charAt(j) == '0')
                return false;
            
            if(abbr.charAt(j) >= 'a' && abbr.charAt(j) <= 'z'){
                if(abbr.charAt(j) == word.charAt(i)){
                    i++;
                    j++;
                } else
                    return false;
            } else {
                while(j<abbr.length() && !(abbr.charAt(j) >= 'a' && abbr.charAt(j) <= 'z')){
                    count = count * 10 + abbr.charAt(j) - '0';
                    j++;
                }

                if(count > 20)
                    return false;
                else {
                    i += count;
                }
                count = 0;
            }
        }

        return (i == word.length() && j == abbr.length());
    }
}