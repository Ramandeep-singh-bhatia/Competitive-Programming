/*class Solution {
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
}*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0;
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        int length = 0;
        List<Integer> result = new ArrayList<>();

        for(char c: p.toCharArray()){
            pCount[c - 'a']++;
        }
        for(; right < s.length(); right++){
            length++;
            sCount[s.charAt(right) - 'a']++;
            while(length > p.length()){
                length--;
                sCount[s.charAt(left) - 'a']--;
                left++;
            }
            if(length == p.length() && Arrays.equals(sCount, pCount)){
                result.add(left);
            }
        }

        return result;
    }
}