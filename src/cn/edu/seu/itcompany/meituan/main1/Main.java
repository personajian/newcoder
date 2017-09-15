package cn.edu.seu.itcompany.meituan.main1;

import java.util.Scanner;

public class Main {
	private static int arr[];
	private static int dp[];
	private static long solution=0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			arr=new int[n+1];
			dp=new int[n+1];
			for (int i = 1; i <= n; i++) {
				arr[i] = in.nextInt();
			}
			int k = in.nextInt();
			
			solute(n,k);
			
			System.out.println(solution);
		}
		in.close();

	}
	
	private static void solute(int n,int k) {
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + arr[i];
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				if ((dp[j + i] - dp[j]) % k == 0)
					solution++;
			}
		}
	}
}