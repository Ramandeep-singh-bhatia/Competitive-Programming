class Solution {
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
}