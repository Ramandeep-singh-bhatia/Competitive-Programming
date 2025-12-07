/*
    Time - Constructor - O(n), shortest - O(k1 + k2) where k1 is the num of occurences of word1 and k2 is the number of occurences of word2
    Space - O(n)
*/

class WordDistance {
    Map<String, List<Integer>> indexMap;
    public WordDistance(String[] wordsDict) {
        indexMap = new HashMap<>();
        for(int i = 0; i < wordsDict.length; i++){
            String word = wordsDict[i];

            if(!indexMap.containsKey(word)){
                indexMap.put(word, new ArrayList<>());
            }

            indexMap.get(word).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> indices1 = indexMap.get(word1);
        List<Integer> indices2 = indexMap.get(word2);

        int i = 0, j = 0;
        int result = Integer.MAX_VALUE;

        while(i < indices1.size() && j < indices2.size()){
            int idx1 = indices1.get(i);
            int idx2 = indices2.get(j);

            int distance = Math.abs(idx1 - idx2);
            result = Math.min(result, distance);

            if(idx1 < idx2){
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */