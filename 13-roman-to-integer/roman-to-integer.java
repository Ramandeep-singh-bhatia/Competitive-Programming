class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        // left to right
        /*int result = 0;
        
        for(int i = 0; i < s.length(); i++){
            int curr = map.get(s.charAt(i));
            int next = 0;
            if(i + 1 < s.length())
                next = map.get(s.charAt(i+1));
            if(curr < next){
                result += next - curr;
                i++;
            } else {
                result += curr;
            }
        }*/

        // right to left

        int result = map.get(s.charAt(s.length() - 1));
        for(int i = s.length() - 2; i >=0; i--){
            int last = map.get(s.charAt(i+1));
            int curr = map.get(s.charAt(i));

            if(curr < last){
                result -= curr;
            } else {
                result += curr;
            }
        }

        return result;
    }
}