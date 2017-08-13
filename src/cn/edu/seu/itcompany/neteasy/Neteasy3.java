package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

public class Neteasy3 {

	private static final long REMAIN = 1000000007;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int k = in.nextInt();
			System.out.println(likeArray(n, k));
		}
		in.close();
	}

	private static long likeArray(int n, int k) {

		// 以k结尾的数，后面添加一位数字，依然能构成满意数
		int[][] arrk = new int[k + 1][k + 1];
		long[][] sumk = new long[n+1][k + 1];
		for (int i = 1; i < k + 1; i++) {
			for (int j = 1; j < k + 1; j++) {
				if (i <= j || i % j != 0) {
					arrk[i][j] = 1;
					// arrk[i][0]+=arrk[i][j];
				}
			}
		}

		return dp(n, k, arrk, sumk);

		/*
		 * long sum = 0; for (int i = 1; i < k + 1; i++) { sum += sumk[n][i]; } return
		 * sum>REMAIN?sum%REMAIN:sum;
		 */
	}

	private static long dp(int n, int k, int[][] arrk, long[][] sumk) {

		for (int i = 1; i < k + 1; i++) {
			sumk[1][i] = 1;
		}

		long sum = 0;

		/*for (int i = 2;i<n+1; i++) {
			for (int j = 1; j < k + 1; j++) {
				//
				for (int l = 1; l < k + 1; l++) 
					sumk[i][j] += sumk[i-1][l] * arrk[j][l];
				sumk[i][j]%=REMAIN;
			}
		}
		
		for (int i = 1; i < k+1; i++) {
			sum=(sum+sumk[n][i])%REMAIN;
		}*/
		
		return sum;
	}
}
