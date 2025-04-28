/*
    One way to solve ths is to keep track of the row and the column for each 0 we encounter when we first traverse through the matrix. In next traversal we can make the value to 0 for all the rows and columns we found in first traversal
    Time complexity - O(m*n)
    Space complexity - O(m + n)
*/


/*class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rows.contains(i) || cols.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}*/

/*
    We are using two sets hence we have space somplexity of O(m + n). If we want to avoid using extra space we can make use of the matrix. 
    
    We can use matrix first row and column as a marker for updating 0's for the corresponding row and columns. Lesta say we have below matrix
    1 1 1
    1 0 1
    1 1 1

    When we loop through we can update the first row and first column to 0 to identify that the whole row and columns will be 0 meaning we can do 
    1 0 1
    0 0 1
    1 1 1

    Next we go through the first row and column and if it is 0 we can update the whole row and column meaning
    1 0 1
    0 0 0
    1 0 1 which will be the final answer but there is an edge case which will fail here
    lest say we have this example
    1 0 1
    1 0 1
    1 1 1

    If we use above algorithm we will go and update its first row and column to 0 and it will be 
    1 0 1
    0 0 1
    1 1 1
    and from here we check if the first row and col has 0 and we update the rest of the numbers for that row and column to 0 
    1 0 1
    0 0 0
    1 0 1 which is wrong, because the original matrix had a 0 for the first row

    i.e. it was 1 0 1 and it should be transformed to 0 0 0
                1 0 1                                 0 0 0
                1 1 1                                 1 0 1.
    We missed to identify that there is already a 0 in the first row which will impact the whole row. So we will need to keep track of that may be using a boolean value.
    We loop through the first row and first column and mark a boolean if we find 0 in first row or col. Next we loop through the matrix again to see if there are any 0's in the rest of the rows and we update the corresponding first row and column to 0.
    When we traverse again, the first row and column 0's will help us update the particular row and column. Once we are done, we check if the first row and column boolean flag is true. based on that we update the first row and column 
    Why we need a flag. We are using first row and column to keep track of which row and columns we need to update. If there is already a 0 in 1st row or column, we will need to keep track of that so that we can update the first row and column later
    Time Complexity  - O(m*n)
    Space Complexity - O(1)
*/

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean fRow = false;
        boolean fCol = false;
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                fCol = true;
                break;
            }
        }

        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0){
                fRow = true;
                break;
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(fRow){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }

        if(fCol){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}