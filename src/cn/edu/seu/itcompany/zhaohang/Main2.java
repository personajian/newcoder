package cn.edu.seu.itcompany.zhaohang;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();

			int result = multi(a, b, n);

			System.out.println(result);
		}
		in.close();
	}

	private static int multi(int a, int b, int n) {
		
		int result = 0;
		
		for (int i = Math.max(a, b); i <= n; i++) {
			if (i % a == 0 && i % b == 0) {
				result++;
			}
		}
		return result;
	}

}
