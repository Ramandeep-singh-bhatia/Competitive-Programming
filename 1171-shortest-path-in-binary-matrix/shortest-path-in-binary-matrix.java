/*
    From {0,0} the pointer can move in any direction, in a grid there are 8 direction hence we can have a direction matrix which stores all 8 directions.base condition will be if {0,0} of the grid is not 0 or {n-1, n-1} is not 0 then return -1.Mentioned in the question
    we can do bfs to travrse throgh the matrix. add the pointer in the matrix to the queue, find all its neighbours which are 0 and update the distance by 1 for the neighbours and add those neighbours to the queue for traversal in next iteration. if we reach the (n-1, n-1) which is end of the grid, we return the distance.

    For neighbours, we navigate in all 8 directions. If the row or column is outside the grid or the value in the grid is not 0, we won't add them to the neighbour matrix

    newrow < 0 || newcolumn < 0 || newrow > grid.length-1 || newcolumn > grid.length-1 || 
        grid[newrow][newcolumn] != 0
*/

/*class Solution {
    int[][] direction = new int[][]{{1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] != 0 || grid[grid.length-1][grid.length-1] != 0)
            return -1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,0});
        grid[0][0] = 1;

        while(!q.isEmpty()){
            int[] cell = q.poll();
            int row = cell[0];
            int column = cell[1];
            int distance = grid[row][column];
            if(row == grid.length-1 && column == grid.length-1)
                return distance;

            for (int[] neighbour : getNeighbours(row, column, grid)){
                int newrow = neighbour[0];
                int newcolumn = neighbour[1];
                q.add(new int[] {newrow, newcolumn});
                grid[newrow][newcolumn] = distance + 1;
            }
        }
        return -1;
    }

    private List<int[]> getNeighbours(int row, int column, int[][] grid){
        List<int[]> neighbour = new ArrayList<>();
        for(int[] dir: direction){
            int newrow = row + dir[0];
            int newcolumn = column + dir[1];

            if(newrow < 0 || newcolumn < 0 || newrow > grid.length-1 || newcolumn > grid.length-1 || grid[newrow][newcolumn] != 0)
                continue;

            neighbour.add(new int[] {newrow , newcolumn});
        }
        return neighbour;
    }
}*/

/*class Solution {
    
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        
        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1}); // Put distance on the queue
        boolean[][] visited = new boolean[grid.length][grid[0].length]; // Used as visited set.
        visited[0][0] = true;
        
        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = cell[2];
            // Check if this is the target cell.
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                if (visited[neighbourRow][neighbourCol]) {
                    continue;
                }
                visited[neighbourRow][neighbourCol] = true;
                queue.add(new int[]{neighbourRow, neighbourCol, distance + 1});
            }
        }
        
        // The target was unreachable.
        return -1;  
    }
    
    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;    
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours; 
    }
    
}*/

class Solution {
    int[][] directions = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}, {1,-1}, {1,1}, {-1,1}, {-1,-1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length-1;
        int n = grid[0].length-1;

        if(grid[0][0] != 0 || grid[m][n] != 0)
            return -1;

        int distance = 1;
        boolean[][] visited = new boolean[m+1][n+1];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                if(row == m && col == n)
                    return distance;
                for(int[] neighbour: getNeighbour(row, col, m, n, grid)){
                    int newRow = neighbour[0];
                    int newCol = neighbour[1];
                    if (visited[newRow][newCol])
                        continue;

                    visited[newRow][newCol] = true;
                    q.add(new int[]{newRow, newCol});
                }
            }
            distance++;
        }
        return -1;
    }

    private List<int[]> getNeighbour(int row, int col, int m, int n, int[][] grid){
        List<int[]> neighbours = new ArrayList<>();
        for(int[] direction: directions){
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if(newRow < 0 || newRow > m || newCol < 0 || newCol > n || grid[newRow][newCol] != 0)
                continue;
            neighbours.add(new int[]{newRow, newCol});
        }

        return neighbours;
    }
}