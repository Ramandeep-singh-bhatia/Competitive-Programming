/*
    Time - O(n*m) where n is the length of the string and m is the maximum value of the digit in the string because the internal while loop will run m time to decode the string of mattern m[string]
    Space - Two stacks - if x is the number of digits in the string and y is the number of alphabets in the string,  then space will be O(x + y) 
*/

class Solution {
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int num = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            } else if(c == '['){
                countStack.push(num);
                stringStack.push(result);
                result = new StringBuilder();
                num = 0;
            } else if(c == ']'){
                StringBuilder sb = stringStack.pop();
                int i = countStack.pop();
                while(i > 0){
                    sb.append(result);
                    i--;
                }
                result = sb;

            } else{
                result.append(c);
            }
        }
        return result.toString();
    }
}