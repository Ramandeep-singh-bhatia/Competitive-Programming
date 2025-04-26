class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        boolean direction = true; // Up direction

        int[] result = new int[m*n];

        int i =0;
        int j =0;

        int r = 0;

        while(i < m && j < n){
            result[r++] = mat[i][j];
            int new_row = i;
            int new_col = j;
            if(direction){
                    new_row = new_row - 1;
                    new_col = new_col + 1;
                } else {
                    new_row = new_row + 1;
                    new_col = new_col - 1;
                }
            if(new_row < 0 || new_row == m || new_col < 0 || new_col == n){
                if(direction){
                   //i += (j+1 == n ? 1 : 0);
                   //j += (j+1 == n ? 0 : 1);

                   if(new_col == n) // Meaning we are at the end of the column. 
                        i += 1; // We will go 1 down the row which will be row +1
                    else // other wise we will go to the right
                        j += 1;
                    direction = false;
                } else {
                    if(new_row == m) // Meaning we are at the end of the column
                        j += 1; // We will go to the right of the column
                    else
                        i += 1; // else we will go 1 down which is row + 1
                    direction = true;
                }

            } else {
                
                i = new_row;
                j = new_col;
            }
        }
        return result;
    }
}