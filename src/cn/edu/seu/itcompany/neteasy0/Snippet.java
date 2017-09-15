package cn.edu.seu.itcompany.neteasy0;

import java.util.Scanner;

public class Snippet {

	private static int t = 0;
	private static int temp = 0;
	private static int n = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		t = in.nextInt();
		for (int i = 0; i < t; i++) {
			n = in.nextInt();
			// int[] a=new int[n];

			int c0 = 0, c1 = 0;

			for (int k = 0; k < n; k++) {
				temp = in.nextInt();
				if (temp % 4 == 0) {
					c0++;
				} else if (temp % 4 == 1 || temp % 4 == 3) {
					c1++;
				} else {

				}
			}
			if (c0 >= c1)
				System.out.println("Yes");
			else
				System.out.println("No");

		}

	}

	public static void solve(int[] a, int n) {
		int c0 = 0, c1 = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] % 4 == 0) {
				c0++;
			} else if (a[i] % 4 == 1 || a[i] % 4 == 3) {
				c1++;
			} else {

			}
		}
		if (c0 >= c1)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

}
