package cn.edu.seu.itcompany.pinduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**六一儿童节
 * @Author personajian
 * @Date 2017/8/2 21:36
 */
public class Solution3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] h = new int[n];
			for (int i = 0; i < n; i++) {
				h[i] = in.nextInt();
			}

			int m = in.nextInt();
			int[] w = new int[m];
			for (int i = 0; i < m; i++) {
				w[i] = in.nextInt();
			}

			System.out.println(result(m, n, h, w));

		}
		in.close();
	}

	private static int result(int m, int n, int[] h, int[] w) {
		Arrays.sort(h);
		Arrays.sort(w);
		int indexH = n - 1;
		int indexW = m - 1;

		int count = 0;
		while (indexH >= 0 && indexW >= 0) {
			if (w[indexW] >= h[indexH]) {
				count++;
				indexH--;
				indexW--;
			} else {
				indexH--;
			}
		}
		return count;
	}

}
