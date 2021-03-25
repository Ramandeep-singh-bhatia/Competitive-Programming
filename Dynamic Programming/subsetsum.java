class subsetsum{
	public boolean canpartition(int[] num, int sum){
		return canpartitionrecursion(num, sum, 0);
	}
	
	public boolean canpartitionrecursion(int[] num, int sum, int curri){
		//base case
		if(sum == 0)
			return true;

		if(sum < 0 || curri >= num.length)
			return false;
	
		if(num[curri] <= sum){
			if(canpartitionrecursion(num, sum-num[curri], curri+1))
				return true;
		}
		
		return canpartitionrecursion(num, sum, curri+1);
	}
	
	//Memoization
	public boolean canpartitionmemo(int[] num, int sum){
		Boolean[][] dp = new Boolean[num.length][sum+1];
		return canpartitionmemorecursion(dp,num,sum,0);
	}
	
	public boolean canpartitionmemorecursion(Boolean[][] dp, int[] num, int sum, int curri ){
		if(sum == 0)
			return true;
		
		if(sum <0 || curri >= num.length)
			return false;
		
		if(dp[curri][sum] != null)
			return dp[curri][sum];

		if(num[curri] <= sum){
			if(canpartitionmemorecursion(dp,num,sum-num[curri], curri+1)){
					dp[curri][sum] = true;
					return true;
			}
		}
		
		dp[curri][sum] = canpartitionmemorecursion(dp, num, sum, curri+1);

		return dp[curri][sum];
	}

	//Tabulation
	public boolean canpartitiontabulation(int[] num, int sum){
		boolean[][] dp = new boolean[num.length][sum+1];
		
		for(int i = 0; i < num.length; i++)
			dp[i][0] = true;
	
		for(int i = 1; i <= sum; i++){
			if(num[0] == i)
				dp[0][i] = true;
			else
				dp[0][i] = false;
		}
	
		for(int i = 1; i < num.length; i++){
			for(int j = 1; j <= sum; j++){
				if(dp[i-1][j])
					dp[i][j] = dp[i-1][j];
				else if(num[i] <= j)
					dp[i][j] = dp[i-1][j-num[i]];
			}
		}
		return dp[num.length-1][sum];
	}

	public static void main(String[] args){
		subsetsum ss = new subsetsum();
		
		int[] num = {1, 2, 3, 7};
		int sum = 6;
		System.out.println(ss.canpartition(num, sum));
		num = new int[]{1, 2, 7, 1, 5};
		sum = 10;
		System.out.println(ss.canpartition(num, sum));
		num = new int[]{1, 3, 4, 8};
		sum = 6;
		System.out.println(ss.canpartition(num, sum));

		/*int[] num = {1, 2, 3, 7};
		int sum = 6;
		System.out.println(ss.canpartitionmemo(num, sum));
		num = new int[]{1, 2, 7, 1, 5};
		sum = 10;
		System.out.println(ss.canpartitionmemo(num, sum));
		num = new int[]{1, 3, 4, 8};
		sum = 6;
		System.out.println(ss.canpartitionmemo(num, sum));*/

		/*int[] num = {1, 2, 3, 7};
		int sum = 6;
		System.out.println(ss.canpartitiontabulation(num, sum));
		num = new int[]{1, 2, 7, 1, 5};
		sum = 10;
		System.out.println(ss.canpartitiontabulation(num, sum));
		num = new int[]{1, 3, 4, 8};
		sum = 6;
		System.out.println(ss.canpartitiontabulation(num, sum));*/
	}
}