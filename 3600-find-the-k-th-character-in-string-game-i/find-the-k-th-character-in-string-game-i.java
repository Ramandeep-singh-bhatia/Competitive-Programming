class Solution {
    public char kthCharacter(int k) {
        StringBuilder s = new StringBuilder();
        s.append("a");
        while (s.length() < k) {
            s.append(addString(s));
        }

        return s.charAt(k-1);
    }

    private String addString(StringBuilder temp){
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < temp.length(); i++){
            ans.append((char)(temp.charAt(i) + 1));
        }
        return ans.toString();
    }
}