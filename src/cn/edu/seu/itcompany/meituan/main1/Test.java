package cn.edu.seu.itcompany.meituan.main1;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int[] a = new int[100020];
			int n = scanner.nextInt();			
			for (int i = 1; i <= n; i++) {
				a[i] = scanner.nextInt();	
			}
			int[] dp = new int[100020];
			dp[0] = 0;
			for(int i = 1; i <= n; i++) {
				dp[i] = dp[i - 1] + a[i];
			}
			int result = 0;
			int k = scanner.nextInt();
			for (int i = 1; i <= n; i++) {
				if(i > result) {
					for (int j = 0; j <= n - i; j++) {
						if ((i > result) && (dp[j + i] - dp[j]) % k == 0) {
							result = i;
						}
					}
				}
			}
			System.out.println(result);
		}
		scanner.close();
	}
}
