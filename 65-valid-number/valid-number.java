/*
A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.

*/

class Solution {
    public boolean isNumber(String s) {
        //Integer - No integer
        //decimal - e. , .7. , .
        //character - ee, e45, 45e 
        //sign - more than 2 signs in the string. 6 + 0, If + or - is in the middle of a string, e must be right before sign, 6-

        boolean seenInt = false;
        boolean seenDec = false;
        boolean seenChar = false;
        int countSign = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c))
                seenInt = true;
            else if (c == 'e' || c == 'E'){
                // if there is no integer before e, if there is already an e or E 
                // before i.e. ee or eE and so on, also if e is the last character 
                // of the string
                // Invalid scenario - character - ee, e45, 45e 
                if(!seenInt || seenChar || i == s.length() - 1)
                    return false;
                seenChar = true;
            }
            else if(c == '-' || c == '+'){
                // Invalid scenarios sign - more than 2 signs in the string. 6 + 0, If + or - is in the middle of a string, e must be right before sign, 6-
                if(countSign == 2) //more than 2 signs in the string
                    return false;
                //If + or - is in the middle of a string, e must be right before sign. +6e-1
                if(i > 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E')
                    return false;
                // 6-, if the sign is the last element on the string
                if(i == s.length() - 1)
                    return false;
                countSign++;
            }
            else if (c == '.'){
                // if e comes before . or there is already a dot in the string 
                // before this
                if(seenChar || seenDec)
                    return false;
                // if its the last element and there is no int before this
                if(i == s.length() - 1 && !seenInt)
                    return false;
                seenDec = true;
            } else
                return false;
        }

        return true;
    }
}

