class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, ans = 0, max = 0;
        int[] c = new int[26];
        for (; right < s.length(); right++){
            c[s.charAt(right) - 'A']++;
            if(c[s.charAt(right) - 'A'] > 0){
                max = Math.max(max, c[s.charAt(right) - 'A']);
            }
            while(right - left + 1 - max > k){
                c[s.charAt(left++) - 'A']--;
            }
            ans = Math.max(ans , right - left + 1);
        }

        return ans;
    }
}