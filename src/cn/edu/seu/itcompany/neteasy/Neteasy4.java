package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

public class Neteasy4 {
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			int n = cin.nextInt();
			int k = cin.nextInt();
			
			long MOD = (long) (1e9 + 7);
			int MAXN = (int) (1e5 + 10);

			long[][] dp = new long[15][MAXN];
			int[] sum = new int[15];
		    for (int i = 1; i <= k; i++) dp[1][i] = 1;
		    sum[1] = k;
		    for (int i = 2; i <= n; i++) {
		        for (int j = 1; j <= k; j++) {
		            for (int t = j * 2; t <= k; t += j)
		                dp[i][j] = (dp[i][j] + dp[i - 1][t]) % MOD;
		            dp[i][j] = ((sum[i - 1] - dp[i][j]) % MOD + MOD) % MOD;
		            sum[i] = (int) ((sum[i] + dp[i][j]) % MOD);
		        }
		    }
		    long ans = 0;
		    for (int i = 1; i <= k; i++)
		        ans = (ans + dp[n][i]) % MOD;
		    
		    System.out.println(ans);

		}
		cin.close();
	}

}

