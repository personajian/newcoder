package cn.edu.seu.itcompany.jd;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();

			int result = slove(n);

			System.out.println(result);
		}
		in.close();

	}

	private static int slove(int n) {
		int a, b, c, d;

		int count = 0;
		for (a = 1; a <= n; a++) {
			for (b = 1; b <= n; b++) {
				for (c = 1; c <= n; c++) {
					for (d = 1; d <= n; d++) {
						if (Math.pow((double) a, (double) b) == Math.pow((double) c, (double) d))
							count++;
					}
				}
			}
		}

		return count;
	}

}
