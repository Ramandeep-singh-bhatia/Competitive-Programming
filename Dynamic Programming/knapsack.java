class knapsack{

	public int solveknapsack(int[] profit, int[] weight, int capacity, int curri){
		//base case
		if(capacity <= 0 || curri >= profit.length)
			return 0;
		
		//condition where we include the item
		int profit1 = 0;
		if( weight[curri] <= capacity)
			profit1 = profit[curri] + solveknapsack(profit, weight, capacity - weight[curri] , curri+1);
		
		//condition where we do not include the item
		int profit2 = solveknapsack(profit, weight, capacity, curri+1);
		
		return Math.max(profit1,profit2);
	}

	public int solveknapsackmemo(int[] profit, int[] weight, int capacity, int curri){
		int[][] dp = new int[profit.length][capacity+1];
		return solveknapsackmemorecursion(dp,profit,weight,capacity, curri);
	}	
	
	public int solveknapsackmemorecursion(int[][] dp,int[] profit, int[] weight, int capacity, int curri){
		if (capacity <= 0 || curri >= profit.length)
			return 0;
		
		if(dp[curri][capacity] != 0)
			return dp[curri][capacity];
	
		int profit1 = 0;
		if(weight[curri] <= capacity)
			profit1 = profit[curri] + solveknapsackmemorecursion(dp, profit,weight, capacity-weight[curri] , curri+1);

		int profit2 = solveknapsackmemorecursion(dp, profit, weight, capacity, curri+1);
	
		dp[curri][capacity] = Math.max(profit1,profit2);

		return dp[curri][capacity];
	}

	public int solveknapsacktabulation(int[] profit, int[] weight, int capacity, int curri){
		int[][] dp = new int[profit.length][capacity+1];
		
		if(capacity <= 0 || curri >= profit.length)
		return 0;

		for(int i = 0; i < profit.length; i++)
			dp[i][0] = 0;

		for(int i = 1; i < capacity+1; i++)
			dp[0][i] = profit[0];
		
		for(int i = 1; i < profit.length; i++){
			for(int j = 1; j < capacity+1; j++){
				int profit1 = 0;
				int profit2 = 0;
				if(weight[i] <= j)
					profit1 = profit[i] + dp[i-1][j- weight[i]];

				profit2 = dp[i-1][j];

				dp[i][j] = Math.max(profit1,profit2);
			}
		}
		return dp[profit.length-1][capacity];	
	}
	
	public static void main(String[] args){
		knapsack ks = new knapsack();
		int[] profit = {1, 6, 10, 16};
		int[] weight = {1, 2, 3, 5};
		int maxprofit = 0;
		maxprofit = ks.solveknapsack(profit, weight, 7, 0);
		System.out.println("7: "+ maxprofit);
		maxprofit = ks.solveknapsack(profit, weight, 6, 0);
		System.out.println("6: "+ maxprofit);
		System.out.println("Using Memoization");
		maxprofit = ks.solveknapsackmemo(profit, weight, 7, 0);
		System.out.println("7: "+ maxprofit);
		maxprofit = ks.solveknapsackmemo(profit, weight, 6, 0);
		System.out.println("6: "+ maxprofit);
		System.out.println("Using Tabulation");
		maxprofit = ks.solveknapsacktabulation(profit, weight, 7, 0);
		System.out.println("7: "+ maxprofit);
		maxprofit = ks.solveknapsacktabulation(profit, weight, 6, 0);
		System.out.println("6: "+ maxprofit);
		 
	}
}