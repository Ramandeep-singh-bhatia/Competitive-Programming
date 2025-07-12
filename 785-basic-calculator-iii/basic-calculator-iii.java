class Solution {
    public int calculate(String s) {
        Queue<Character> q = new LinkedList<>();
        for(char c : s.toCharArray()){
            q.offer(c);
        }
        return cal(q);
    }
    public int cal(Queue<Character> q) {
        Stack<Integer> numberStack = new Stack<>();
        int currentNum = 0;
        char operator = '+';

        while(!q.isEmpty()){
            char c = q.poll();
            if(c == '('){
                currentNum = cal(q);
            } else if(c >= '0' && c <= '9'){
                currentNum = (currentNum * 10) + (c-'0');
            }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                insertIntoStack(operator, numberStack, currentNum);
                currentNum = 0;
                operator = c;
            } else if (c == ')'){        
                break;
            }  
        } 

        insertIntoStack(operator, numberStack, currentNum);
        int result = 0;
        while(!numberStack.isEmpty()){
            result += numberStack.pop();
        }

        return result;
    }

    private void insertIntoStack(char operator, Stack<Integer> numberStack, int currentNum){
        if(operator == '+'){
            numberStack.push(currentNum);
        } else if(operator == '-'){
            numberStack.push(-currentNum);
        } else if(operator == '*'){
            numberStack.push(numberStack.pop() * currentNum);
        } else {
            numberStack.push(numberStack.pop() / currentNum);
        }
    }
}