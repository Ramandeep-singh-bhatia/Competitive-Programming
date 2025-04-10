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