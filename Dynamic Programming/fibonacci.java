class fibonacci {

	public int calculatefibonacci (int n){
		if (n <2)
		     return n;
		return calculatefibonacci(n-1) + calculatefibonacci(n-2);
	}

	//memoization
	public int calculatefibonaccimemo(int n){
		int[] memoize = new int[10];
		return calculatefibonaccimemorecursion(memoize, n);
	}

	public int calculatefibonaccimemorecursion(int[] memoize, int n){
		if(n < 2)
		    return n;
	
		if(memoize[n] != 0)
			return memoize[n];

		memoize[n] = calculatefibonaccimemorecursion(memoize, n-1) + calculatefibonaccimemorecursion(memoize, n-2);
		return memoize[n];
	}

	//tabulation
	public int calculatefibonaccitabulation(int n){
		int dp[] = new int[10];
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i = 2; i <= n; i++){
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		return dp[n];
	}
	
	public static void main (String[] args){
		fibonacci f = new fibonacci();
		System.out.println(" 5th fibonacci is "+f.calculatefibonacci(5));
		System.out.println(" 6th fibonacci is "+f.calculatefibonacci(6));
		System.out.println(" 7th fibonacci is "+f.calculatefibonacci(7));
		System.out.println("Using Memoization");
		System.out.println(" 5th fibonacci is "+ f.calculatefibonaccimemo(5));
		System.out.println(" 6th fibonacci is "+ f.calculatefibonaccimemo(6));
		System.out.println(" 7th fibonacci is "+ f.calculatefibonaccimemo(7));
		System.out.println("Using Tabulation");
		System.out.println(" 5th fibonacci is "+ f.calculatefibonaccitabulation(5));
		System.out.println(" 6th fibonacci is "+ f.calculatefibonaccitabulation(6));
		System.out.println(" 7th fibonacci is "+ f.calculatefibonaccitabulation(7));
	}
}