package cn.edu.seu.itcompany.huyu;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int n = sc.nextInt();
		
		 int[] cards = new int[n];
		  for(int i=0;i<n;i++){
			  cards[i]=sc.nextInt();
		  }
		  int m = sc.nextInt();
		//int[] cards = { 1, 2, 3, 4, 5 };

		int[][] res = new int[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m + 1; j++) {
				res[i][j] = 1;
			}
		}

		getResult(cards, res, m, n);
	}

	public static void getResult(int[] cards, int[][] res, int m, int n2) {

		int n = cards.length;
		for (int i = 0; i < n; i++) {

			for (int j = m; j >= 0; j--) {
				
				if (j - cards[i] >= 0) {

					res[i + 1][j] = Math.max(res[i][j], res[i][j - cards[i]] * cards[i]);

				} else {

					res[i + 1][j] = res[i][j];
					
				}

			}

		}

		int max = 0;
		for (int i = 0; i <=n; i++) {
			if (res[i][m] > max) {
				max = res[i][m];
			}
		}

		System.out.println(max);

	}

}
