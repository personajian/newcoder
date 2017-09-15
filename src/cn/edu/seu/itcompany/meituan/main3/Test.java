package cn.edu.seu.itcompany.meituan.main3;

import java.util.*;

public class Test {

	private static int arr[]=new int[100002];
	private static int dp[]=new int[100002];
	private static long solution = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {

			int n = in.nextInt();
			for (int i = 1; i <= n; i++) {
				arr[i] = in.nextInt();
			}
			int k = in.nextInt();

			solute(n, k);

			System.out.println(solution);
		}
		in.close();
	}

	private static void solute(int n, int k) {
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + arr[i];
		}
		
		for (int i = 1; i <= n; i++) {
			if (i > solution) {
				for (int j = 0; j <= n - i; j++) {
					solution = i;
				}
			}
		}
	}
}
