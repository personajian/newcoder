package cn.edu.seu.itcompany.neteasy0;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext()) {
			int t = in.nextInt();
			int i1, i2, i3, n = 0;
			int j;
			for (int i = 0; i < t; i++) {
				i1 = 0;
				i2 = 0;
				i3 = 0;

				n = in.nextInt();

				for (j = 0; j < n; j++) {
					int temp = in.nextInt();
					if (temp % 4 == 0) {
						i3++;
					} else if (temp % 2 == 0) {
						i2++;
					} else {
						i1++;
					}
				}
				if (i1 > i3 + 1) {
					System.out.println("No");
				} else if ((i2 == 1 && i1 == i3 + 1)) {
					System.out.println("No");
				} else
					System.out.println("Yes");
			}
		}
		in.close();

	}

}
