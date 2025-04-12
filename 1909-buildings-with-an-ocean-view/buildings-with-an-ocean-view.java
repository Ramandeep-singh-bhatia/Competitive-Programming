class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> result = new ArrayList<>();

        int highest = heights[heights.length-1];
        result.add(heights.length-1);
        for(int i = heights.length-2; i >=0; i--){
            if(heights[i] > highest){
                highest = heights[i];
                result.add(i);
            }
        }

        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            answer[i] = result.get(result.size() - 1 - i);
        }
        
        return answer;
    }
}