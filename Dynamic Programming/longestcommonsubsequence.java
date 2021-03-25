class longestcommonsubsequence{
	public int findlcslength(String s1, String s2){
		return findlcslengthrecursive(s1, s2 , 0,0);
	}

	public int findlcslengthrecursive(String s1, String s2, int i1, int i2){
		if(i1 == s1.length() || i2 == s2.length())
			return 0;
		if(s1.charAt(i1) == s2.charAt(i2))
			return 1+ findlcslengthrecursive(s1,s2,i1+1,i2+1);

		int c1 = findlcslengthrecursive(s1,s2,i1,i2+1);
		int c2 = findlcslengthrecursive(s1,s2,i1+1,i2);

		return Math.max(c1,c2);
	}

//Memoization

	public int findlcslengthmemo(String s1, String s2){
		Integer[][] dp = new Integer[s1.length()][s2.length()];
		return findlcslengthmemorecursive(dp,s1,s2,0,0);
	}

	public int findlcslengthmemorecursive(Integer[][] dp, String s1, String s2, int i1, int i2){
		if(i1 == s1.length() || i2 == s2.length())
			return 0;

		if(dp[i1][i2] == null){
			if(s1.charAt(i1) == s2.charAt(i2))
				dp[i1][i2] = 1+ findlcslengthmemorecursive(dp,s1,s2,i1+1,i2+1);
			else{
				int c1 = findlcslengthmemorecursive(dp,s1,s2,i1,i2+1);
				int c2 = findlcslengthmemorecursive(dp,s1,s2,i1+1,i2);

				dp[i1][i2] = Math.max(c1,c2);
			}
		}

		return dp[i1][i2];
	}

//Tabulation

	public int findlcslengthtab(String s1, String s2){
		int[][] dp = new int[s1.length() +1][s2.length()+1];
	
		for(int i = 1; i<= s1.length(); i++){
			for(int j = 1; j<= s2.length(); j++){
				if(s1.charAt(i-1) == s2.charAt(j-1))
					dp[i][j] = 1+dp[i-1][j-1];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		return dp[s1.length()][s2.length()];
	}

	public static void main(String[] args){
		longestcommonsubsequence lcs = new longestcommonsubsequence();
		System.out.println(lcs. findlcslength("abdca", "cbda"));
		System.out.println(lcs. findlcslength("passport", "ppsspt"));

		System.out.println(lcs. findlcslengthmemo("abdca", "cbda"));
		System.out.println(lcs. findlcslengthmemo("passport", "ppsspt"));

		System.out.println(lcs. findlcslengthtab("abdca", "cbda"));
		System.out.println(lcs. findlcslengthtab("passport", "ppsspt"));
	}
}