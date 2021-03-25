class partitionset{
	// we can solve the problem similar to knapsack. In knapsack we had a capacity and weight was reduced from the capacity everytime we included that value.
	// Similarly in this problem two sunsets should be equal in sum. First we can calculate the total sum of all the values in num array and then cheak that sum of each subset
	// should be equal to each other and equal to half the sum.
	
	public boolean canpartition(int[] num){
		
		int sum = 0;
		
		// calculate the sum of all the values in num array 
		for (int i = 0; i < num.length; i++){
			sum += num[i];
		}
		
		// Since the sum of the two subsets have to be equal, the total sum of num array cannot be an odd number. If odd return false.
		if(sum %2 != 0)
			return false;
		
		// In Knapsack we reduced the capacity everytime we included an element. Here since we had only num array as in parameter we take a seperate method
		// which takes sum/2 (because both subset should be same) and starting index as in parameter and call this method recursively.
		return canpartitionrecursion(num, sum/2, 0);
	}
	
	
	public boolean canpartitionrecursion(int[] num, int sum, int curri){
		
		// Since sum will be reduced by the number selected. if the sum reaches to 0 we return true. This is a base case.
		if (sum == 0)
			return true;
		
		// Since we are increasing the index in every iteration, return false if the index is >= num array length 
		if(sum < 0 || curri >= num.length)
			return false;

		// This the condition when the element from the num length is selected.
		// First check that the value is less than the sum. Since we are selecting the value we will reduce it from the sum and increase the index by 1.
		// It return true if th evalue is selected.
		if(num[curri] <= sum){
			if(canpartitionrecursion(num, sum-num[curri], curri+1))
				return true;	
		}
		
		// This is when that particular value is not selected. We are not reducing the sum only increasing the index.
		return canpartitionrecursion(num, sum, curri+1);
	}

	// Implementing th eproblem using memoization (Top down approach)
	// Here we will maintain a table and check for the value in the table. If the value exist we will not calculate the value again.	
	public boolean canpartitionmemo(int[] num){
		int sum = 0;
		for(int i = 0; i < num.length; i++)
			sum += num[i];
	
		if(sum %2 != 0)
			return false;

		// Table to store the value at each iteration
		Boolean[][] dp = new Boolean[num.length][sum/2+1];
		
		return canpartitionmemorecursion(dp, num, sum/2, 0);
	}

	public boolean canpartitionmemorecursion(Boolean[][] dp, int[] num, int sum, int curri){
		
		// Base case
		if(sum == 0)
			return true;
		if(sum < 0 || curri >= num.length)
			return false;
	
		// Checking if the value in the table exist or not. If exist, the value will not be null and will retun the stored value.
		if(dp[curri][sum] != null)
			return dp[curri][sum];

		// First condition to select that particular value, hence reducing the value from the sum. We are also storing it in dp table.
		if(num[curri] <= sum){
			if(canpartitionmemorecursion(dp, num, sum-num[curri], curri+1)){
				dp[curri][sum] = true;
				return true;
			}
		}
		
		// Second condition when not selecting the value hence sum is not reduced. The value is stored in the dp table
		dp[curri][sum] = canpartitionmemorecursion(dp, num, sum, curri+1);

		return dp[curri][sum];
	}

	
	// Solving the proble using tabluation method (Bottom up approach)
	public boolean canpartitiontabulation(int[] num){
		int sum = 0;		
		for (int i = 0; i <num.length; i++)
			sum += num[i];
		
		if (sum%2 != 0 )
			return false;
		
		sum = sum/2;
		
		boolean[][] dp = new boolean[num.length][sum+1];
	
		// First column will be populated to true 	
		for(int i = 0; i < num.length; i++)
			dp[i][0] = true;
		
		// For first row, if the number on 0th index is equal to the sum, it stores true else false.
		for(int i = 1; i <= sum; i++){
			if(num[0] == i)
				dp[0][i] = true;
			else
				dp[0][i] = false; 
		}
	
		
		for(int i = 1; i< num.length; i++){
			for(int j =1; j <= sum; j++){
				// When we are not including that value, sum will not be reduced. Only the index is reduced
				if(dp[i-1][j])
					dp[i][j] = dp[i-1][j];
				// When included, sum is reduced by the number on that index and index is also reduced.
				else
					dp[i][j] = dp[i-1][j-num[i]];
			}
		}

		//returning the last corner of the table
		return dp[num.length-1][sum];
	}
	
	public static void main (String[] args){
		partitionset ps = new partitionset();
		int[] num = {1, 2, 3, 4};
		System.out.println(ps.canpartition(num));
		num = new int[]{1, 1, 3, 4, 7};
		System.out.println(ps.canpartition(num));
		num = new int[]{2, 3, 4, 6};
		System.out.println(ps.canpartition(num));

		/*int[] num = {1, 2, 3, 4};
		System.out.println(ps.canpartitionmemo(num));
		num = new int[]{1, 1, 3, 4, 7};
		System.out.println(ps.canpartitionmemo(num));
		num = new int[]{2, 3, 4, 6};
		System.out.println(ps.canpartitionmemo(num));*/

		/*int[] num = {1, 2, 3, 4};
		System.out.println(ps.canpartitiontabulation(num));
		num = new int[]{1, 1, 3, 4, 7};
		System.out.println(ps. canpartitiontabulation(num));
		num = new int[]{2, 3, 4, 6};
		System.out.println(ps. canpartitiontabulation(num));*/

	} 
}