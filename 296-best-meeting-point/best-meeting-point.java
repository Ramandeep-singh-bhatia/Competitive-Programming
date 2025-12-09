/*
Time - O(m*n + m * n Log m*n)
Space - O(m * n)

Worst case will be when there is 1 at all the rows and columns
*/

class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        Collections.sort(cols);

        int medianRow = rows.get(rows.size() / 2);
        int medianCol = cols.get(cols.size() / 2);

        int result = 0;
        for(int row: rows){
            result += Math.abs(row - medianRow);
        }

        for(int col: cols){
            result += Math.abs(col - medianCol);
        }

        return result;
    }
}