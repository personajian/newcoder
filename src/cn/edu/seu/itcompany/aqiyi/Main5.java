package cn.edu.seu.itcompany.aqiyi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int result = getResult(n, m);
			System.out.println(result);
		}
		in.close();
	}

	public static int getResult(int m, int n) {
		double res;
		double num1;
		int res1, num2;
		int count = 0;
		boolean flag;
		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {
				res = Math.sqrt(i * j);
				res1 = (int) res;
				num1 = (int) Math.sqrt(j);
				num2 = (int) num1;
				if (num1 == num2) {
					flag = false;
				} else {
					flag = true;
				}

				if (res1 == res) {
					count++;
					// System.out.println(i+"---"+j);

					if (flag) {
						j = j * 2;
					}
					//

				}

			}
		}
		return count;

	}

}
