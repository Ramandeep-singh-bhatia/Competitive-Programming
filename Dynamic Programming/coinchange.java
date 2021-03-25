class coinchange{
	public static int coinchange(int[] dominations, int total){
		return coinchangerecursion (dominations, total, 0);
	}

	public static int coinchangerecursion (int[] dominations, int total, int currindex){
		if(total<0 || currindex >= dominations.length)
			return 0;
		if(total==0)
			return 1;

		int count1 = 0;

		if(dominations[currindex] <= total)
			count1 = coinchangerecursion(dominations , total-dominations[currindex], currindex);

		int count2 = coinchangerecursion(dominations , total, currindex+1);

		return count1+count2;
	}

	

	public static void main (String[] args){
		int[] dominations = {1,2,3};
		int total = 5;
		System.out.println(coinchange(dominations,total));
	}
}