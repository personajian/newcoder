package cn.edu.seu.itcompany.meituan.main1;

import java.util.Scanner;

public class test1 {
	static int a[] = new int[100010];
	static long dp[] = new long[100010];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, k, i, j;
		long res;
		n = scanner.nextInt();
		for (i = 1; i <= n; i++) {
			a[i] = scanner.nextInt();
		}
		k = scanner.nextInt();

		while (true) {
			dp[0] = 0;
			res = 0;
			for (i = 1; i <= n; i++) {
				dp[i] = dp[i - 1] + a[i];
			}
			for (i = 1; i <= n; i++) {
				for (j = 0; j <= n - i; j++) {
					if ((dp[j + i] - dp[j]) % k == 0)
						res++;
				}
			}
			System.out.println(res);
		}
	}
}