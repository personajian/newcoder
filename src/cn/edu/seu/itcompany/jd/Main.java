package cn.edu.seu.itcompany.jd;

import java.util.Scanner;

public class Main {

	private static int result = 1;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			String str = in.nextLine();
			char[] chs = str.toCharArray();
			int n = chs.length;

			solve(chs, n);

			System.out.println(result);
		}
		in.close();
	}

	private static void solve(char[] chs, int n) {

		int count = 0;

		for (int i = 0; i < n; i++) {
			if ('(' == chs[i]) {
				count++;
			}
			if (')' == chs[i]) {
				result *= count;
				count--;
			}
		}
	}
}
