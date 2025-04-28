class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> hm = new HashMap<>();
        hm.put('(', ')');
        hm.put('{', '}');
        hm.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(hm.containsKey(c)){
                stack.push(hm.get(c));
            } else if (hm.containsValue(c)){
                if(stack.isEmpty() || stack.pop() != c)
                    return false;
            }
        }
         // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}