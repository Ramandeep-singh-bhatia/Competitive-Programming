class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0, sum = 0;
        int[] pcount = new int[26];
        int[] scount = new int[26];
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < p.length(); i++){
            pcount[p.charAt(i) - 'a']++;
        }
        for(; right < s.length(); right++){
            scount[s.charAt(right) - 'a']++;

            if(right - left + 1 > p.length()){
                scount[s.charAt(left) - 'a']--;
                left++;
            }

            if(Arrays.equals(pcount, scount)){
                result.add(left);
            }
        }

        return result;
    }
}