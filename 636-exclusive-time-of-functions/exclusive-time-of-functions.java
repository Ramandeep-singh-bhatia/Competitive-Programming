class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        int prev = 0;

        for(String log : logs){
            String[] l = log.split(":");
            int id = Integer.valueOf(l[0]);
            int timeStamp = Integer.valueOf(l[2]);
            
            if(l[1].equals("start")){
                if(!stack.isEmpty()){
                    result[stack.peek()] += timeStamp - prev;
                }
                stack.push(id);
                prev = timeStamp;
            } else {
                result[stack.pop()] += timeStamp - prev + 1;
                prev = timeStamp + 1;
            }
        }
        return result;
    }
}