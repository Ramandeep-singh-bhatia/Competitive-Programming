/*
    Scan the input string s from left to right and evaluate the expressions based on the following rules

1. If the current character is a digit 0-9 ( operand ), add it to the number currentNumber.
2. Otherwise, the current character must be an operation (+,-,*, /). Evaluate the expression based on the type of operation.
3. Addition (+) or Subtraction (-): We must evaluate the expression later based on the next operation. So, we must store the currentNumber to be used later. Let's push the currentNumber in the Stack.
4. Multiplication (*) or Division (/): Pop the top values from the stack and evaluate the current expression. Push the evaluated value back to the stack.
    Time - O(n)
    Space - O(n)
*/

/*class Solution {
    public int calculate(String s) {
        // Stack to keep track of the current number
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        // Operation feild to maintain the current operation. for 3+2*4 - When the char is at + the operation is still + and when it reaches * the operation is still +. At the end of the loop we update it to *. If you look at the if condition, only when i == s.length()-1, irrespective of whether the char is a operation or a number, it will got and do the processing. Technically the last number is always a digit, so the current operation will be * and the current number will be 24 and the stack will have 3,2.
        char operation = '+';
        for(int i =0; i < s.length(); i++){
            char c = s.charAt(i);
            // c-'0' converts the character to integer. 
            if(Character.isDigit(c)){
                currentNumber = currentNumber * 10 + (c - '0');
            }
            // for character is operation on white space we will have to perform the operation. We will only be updating the operation at the end of the processing to make sure when we reach an operation we know what the previos operation was. Reason is supppose we have * before a number then we will have to multiply before pushing it back to stack. Unless its the last char because last char will always be a number hence we have to do the operation and push it to the stack. for + and - simply push because we have to evaluate this expression later.
            if(!Character.isDigit(c) && !Character.isWhitespace(c) || i == s.length() -1){
                if(operation == '+')
                    stack.push(currentNumber);
                else if(operation == '-')
                    stack.push(-currentNumber);
                else if(operation == '*')
                    stack.push(stack.pop() * currentNumber);
                else if (operation == '/')
                    stack.push(stack.pop() / currentNumber);
                currentNumber = 0;
                operation = c;
            }
        }
        int result = 0;
        while (!stack.isEmpty()){
            result += stack.pop();
        }

        return result;
    }
}*/

class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = (currentNumber * 10) + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = c;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }
}
