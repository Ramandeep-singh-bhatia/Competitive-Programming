class longestcommonsubstring{
//Recursion
	public int findlcslength(String s1, String s2){
		return findlcslengthrecursive(s1, s2, 0, 0, 0);
	}
	
	public int findlcslengthrecursive(String s1, String s2, int i1, int i2, int count){
		if(i1 == s1.length() || i2 == s2.length())
			return count;

		if(s1.charAt(i1) == s2.charAt(i2))
			count = findlcslengthrecursive(s1,s2,i1+1, i2+1,count+1);
		
		int c1 = findlcslengthrecursive(s1,s2,i1, i2+1,0);
		int c2 = findlcslengthrecursive(s1,s2,i1+1, i2,0);

		return Math.max(count, Math.max(c1,c2));
	}

//Memoization
	public int findlcslengthmemo(String s1, String s2){
		Integer[][][] dp = new Integer[s1.length()][s2.length()][Math.min(s1.length(), s2.length())];
		return findlcslengthmemorecursion (dp, s1, s2, 0,0,0);
	}

	public int findlcslengthmemorecursion(Integer[][][] dp, String s1, String s2, int i1, int i2, int count){
		if(i1 == s1.length() || i2 == s2.length())
			return count;
		if(dp[i1][i2][count] == null){
			int c1 = count;
			if(s1.charAt(i1) == s2.charAt(i2))
				c1 = findlcslengthmemorecursion(dp,s1,s2,i1+1, i2+1, count+1);

			int c2 = findlcslengthmemorecursion(dp,s1,s2,i1,i2+1,0);
			int c3 = findlcslengthmemorecursion(dp,s1,s2,i1+1,i2,0);

			dp[i1][i2][count] = Math.max(c1, Math.max(c2,c3));
		}
		return dp[i1][i2][count];
	}

//Tabulation

	public int findlcslengthtab(String s1, String s2){
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		int maxlength = 0;
		
		for(int i = 1; i<= s1.length(); i++){
			for (int j = 1; j<= s2.length(); j++){
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = 1+dp[i-1][j-1];
					maxlength = Math.max(maxlength, dp[i][j]);
				}
			}
		}
		return maxlength;
	}

	

	public static void main(String[] args){
		longestcommonsubstring lcs = new longestcommonsubstring();
		System.out.println(lcs.findlcslength("abdca", "cbda"));
		System.out.println(lcs.findlcslength("passport", "ppsspt"));

		System.out.println(lcs.findlcslengthmemo("abdca", "cbda"));
		System.out.println(lcs.findlcslengthmemo("passport", "ppsspt"));

		System.out.println(lcs.findlcslengthtab("abdca", "cbda"));
		System.out.println(lcs.findlcslengthtab("passport", "ppsspt"));
	}
}