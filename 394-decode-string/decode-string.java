/*
    Time - O(n*m) where n is the length of the string and m is the maximum value of the digit in the string because the internal while loop will run m time to decode the string of mattern m[string]
    Space - Two stacks - if x is the number of digits in the string and y is the number of alphabets in the string,  then space will be O(x + y) 
*/

/*class Solution {
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
}*/

/*
    To save space and avoid using stack, we can use recursion to solve this. We divide the problems into subproblems, each subproblem will end when we encounter a ] bracket. Lets say we have this subproblem 2[a]
    We can keep track of the digit and the characters. For each open bracket, we get a string follwoed by a closed bracket. When we encounter teh open bracket, we can call the function recursively and return the string enclosed within the bracket. We can loop num times to add the string to the result. This num will keep track of the digit before the string and open close bracket. We can recursively call the function to get the string and then add the string to the result based on the number before the open close bracket.

     Time - O(n*m) where n is the length of the string and m is the maximum value of the digit in the string because the internal while loop will run m time to decode the string of mattern m[string]
    Space - Recursion space - if y is the number of alphabets enclosed in the bracket in the string, then space will be O(y). Max it can be n whihc is the length of the string
*/

class Solution {
    // index tracker for our current character in the string
    private int index = 0;
    
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        
        // Process the string until we reach the end or a closing bracket
        while (index < s.length() && s.charAt(index) != ']') {
            char currentChar = s.charAt(index);
            
            // Case 1: Current character is a letter - just add to result
            if (Character.isLetter(currentChar)) {
                result.append(currentChar);
                index++;
            }
            // Case 2: Current character is a digit - need to decode a repetition pattern
            else if (Character.isDigit(currentChar)) {
                // Extract the number (could be multiple digits)
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + (s.charAt(index) - '0');
                    index++;
                }
                
                // Skip the opening bracket '['
                index++; // index now points to the character after '['
                
                // Recursively decode the content inside brackets
                String decodedSubstring = decodeString(s);
                
                // Skip the closing bracket ']'
                index++; // index now points to the character after ']'
                
                // Append the decoded substring 'num' times
                for (int i = 0; i < num; i++) {
                    result.append(decodedSubstring);
                }
            }
        }
        
        return result.toString();
    }
}