class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for(int i = 0; i < matrix.length; i++){
            pq.offer(new int[]{matrix[i][0], i , 0});
        }

        int count = 0;
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            count++;
            if(count == k){
                return curr[0];
            }
            int row = curr[1];
            int col = curr[2];

            if(col + 1 < matrix[0].length){
                pq.offer(new int[]{matrix[row][col + 1], row, col +1});
            }
        }

        return -1;
    }
}