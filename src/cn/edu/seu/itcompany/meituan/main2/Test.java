package cn.edu.seu.itcompany.meituan.main2;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();			
			int[] a = new int[n+1];
			int[] dp = new int[n+1];
			for (int i = 1; i <= n; i++) {
				a[i] = in.nextInt();	
			}
			
			dp[0] = 0;
			for(int i = 1; i <= n; i++) {
				dp[i] = dp[i - 1] + a[i];
			}
			int solution = 0;
			int k = in.nextInt();
			for (int i = 1; i <= n; i++) {
				if(i > solution) {
					for (int j = 0; j <= n - i; j++) {
						if ((i > solution) && (dp[j + i] - dp[j]) % k == 0) {
							solution = i;
						}
					}
				}
			}
			System.out.println(solution);
		}
		in.close();
	}
}
