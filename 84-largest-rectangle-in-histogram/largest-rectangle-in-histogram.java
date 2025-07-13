class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nl = nsl(heights, n);
        int[] nr = nsr(heights, n);
        int maxArea = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            maxArea = Math.max(maxArea, (nr[i] - nl[i] - 1) * heights[i]);
        }

        return maxArea;
    }

    private int[] nsl(int[] heights, int n){
        int[] nl = new int[n];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < n; i++){
            
            while(!s.isEmpty() && heights[s.peek()] >= heights[i])
                s.pop();
            
            nl[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }
        return nl;
    }

    private int[] nsr(int[] heights, int n){
        int[] nr = new int[n];
        Stack<Integer> s = new Stack<>();
        for(int i = n-1; i >= 0; i--){
            
            while(!s.isEmpty() && heights[s.peek()] >= heights[i])
                s.pop();
            
            nr[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }
        return nr;
    }
}