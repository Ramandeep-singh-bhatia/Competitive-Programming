/*
    Time - (n - 10) * 10 where n-10 is for the loop and O(10) is for substring. For every n-10 characters we do a substring to get the 10 length string.
    Space - O(n-10)
*/

/*class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        List<String> result = new ArrayList<>();
        if(n < 10)
            return result;
        Map<String, Integer> dnaMap = new HashMap<>();
        for(int i = 0; i <= n - 10; i++){
            String subString = s.substring(i, i+10);

            dnaMap.put(subString, dnaMap.getOrDefault(subString, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry : dnaMap.entrySet()){
            if(entry.getValue() > 1){
                result.add(entry.getKey());
            }
        }

        return result;
    }
}*/

/*
    
Time Complexity: O(n)

    Building initial hash: O(10) = O(1) - constant time for first 10 characters
    Sliding window loop: O(n - 10) = O(n) iterations
    Each iteration does:
        Bit operations (AND, shift, OR): O(1)
        HashMap get/put: O(1) average case
        String substring (only when count == 1): O(10) = O(1)


Space Complexity: O(n)

    dnaMap HashMap: In worst case, all substrings are unique, so we store up to (n - 9) integer keys → O(n)
    result HashSet: In worst case, all substrings appear exactly twice → O(n) strings, each 10 characters → O(n)
    charToInt map: O(4) = O(1) constant
    Other variables: O(1)
*/

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        Set<String> result = new HashSet<>();
        if(n < 10)
            return new ArrayList<>();
        
        Map<Character, Integer> charToInt = new HashMap<>();
        charToInt.put('A', 0);
        charToInt.put('C', 1);
        charToInt.put('G', 2);
        charToInt.put('T', 3);

        Map<Integer, Integer> dnaMap = new HashMap<>();

        int hash = 0;
        for(int i = 0; i < 10; i++){
            hash = (hash << 2) | charToInt.get(s.charAt(i));
        }
        dnaMap.put(hash, 1);
        int slidemask = (1 << 18) - 1;

        for(int i = 10; i < n; i++){
            hash = ((hash & slidemask) << 2) | charToInt.get(s.charAt(i));
            dnaMap.put(hash, dnaMap.getOrDefault(hash, 0) + 1);

            if(dnaMap.get(hash) > 1){
                result.add(s.substring(i - 9, i + 1));
            }
        }

        return new ArrayList<>(result);
    }
}