/*
    A valid paranthesis is open closed bracket pair. We can use stack to keep track of the open and close bracket indexes. Everytime we get an open bracket we store the index in a stack. when we get the closed bracket, we see if the stack is empty or not. If it is not empty we check if the stack peek is open bracket. If it is we remove the index from the stack. Else, push the closed bracket. This way we can keep track of all the parantheses which is causing the string to be invalid. 
    Next we can convert the string to string builder and remove all the characters fromt he string builder that are in the stack.

    Time - O(n)
    Space - O(n)
*/

/*class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s.length() == 0)
            return s;
        StringBuilder result = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                stack.push(i);
            }
            
            if(s.charAt(i) == ')'){
                if(stack.isEmpty() || s.charAt(stack.peek()) != '('){
                    stack.push(i);
                } else {
                    stack.pop();
                }
            }
        }

        while(!stack.isEmpty()){
            int num = stack.pop();
            result.deleteCharAt(num);
        }

        return result.toString();
    }
}*/

/*
    We can solve this without using stack. We can have a variable that is incremented when we get a '(' in the string. When we get a ')' the variable is decremented. When checking for ')' if the balance is already 0 we know that this close bracket is invalid so we don't decrement the balance and also do not add it to the string builder. At the end of the loop we will have all the invalid ) removed.
    Now if the balance is > 0, we know that the balance was incremented when we get ) and if it is still > 0 that means there are few ( for which there is no ) meaning they are invalid. So we loop through the string builder from the end and remove all the open brackets until the balance is 0. 
    Time - O(n) for two loops and conversting stringbuilder to string
    Space - O(n) String builder
*/

class Solution {

    public String minRemoveToMakeValid(String s) {

        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < s.length(); i++) { // O(n)
            char c = s.charAt(i);
            if (c == '(') {
                balance++;
            } if (c == ')') {
                if (balance == 0) 
                    continue;
                balance--;
            }
            sb.append(c);
        }

        for(int i = sb.length() - 1; i >= 0; i--){ // O(n)
            char c = sb.charAt(i);
            if(c == '(' && balance > 0){
                sb.deleteCharAt(i);
                balance--;
            }
        }

        return sb.toString(); // O(n)
    }
}