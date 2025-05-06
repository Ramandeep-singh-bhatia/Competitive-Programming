/*
    To remove duplicate with each responses, we can use set and add all the responses in a set.
    We can use map to store the response and the frequency of the response among all the responses.
    We can either use prioirty queue or simply scan through the map to find the response which has the maximum frequency.
    In case of tie, we get the lexicographically smaller response
    If n = number of inner lists in responses
    m = average number of strings in each inner list

    Time - O(n*m)
    Space - O(n*m)
*/

class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        if(responses==null || responses.size()==0)
            return "";
        Map<String,Integer> map=new HashMap<>();
        for (List<String> response : responses) {
            Set<String> set=new HashSet<>(response);
            for(String str: set) {
                map.put(str,map.getOrDefault(str,0)+1);
            }
        }

        /*PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<> (
            (a,b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()
        );

        for(Map.Entry<String,Integer> e: hm.entrySet())
            {
                pq.add(e);
            }
        return pq.poll().getKey();*/

        int maxFrequency = 0;
        String ans = "";
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            if(value > maxFrequency || (value == maxFrequency && key.compareTo(ans) < 0)){
                ans = key;
                maxFrequency = value;
            }
        }

        return ans;
    }
}