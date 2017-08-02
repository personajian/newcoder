package cn.edu.seu.itcompany.envision;

import java.util.*;

/**矩阵相乘
 * @author personajian
 *
 */
public class S2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			int y = in.nextInt();
			int z = in.nextInt();
			int[][] matrixA = new int[x][y];
			int[][] matrixB = new int[y][z];
			int[][] matrixC = new int[x][z];
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					matrixA[i][j] = in.nextInt();
				}
			}
			for (int i = 0; i < y; i++) {
				for (int j = 0; j < z; j++) {
					matrixB[i][j] = in.nextInt();
				}
			}
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < z; j++) {
					for (int k = 0; k < y; k++) {
						matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
					}
				}
			}
			for (int i = 0; i < x; i++) {
				StringBuilder s = new StringBuilder();
				for (int j = 0; j < z; j++) {
					if (j == z - 1) {
						s.append(matrixC[i][j]);
					} else {
						s.append(matrixC[i][j] + " ");
					}
				}
				System.out.println(s.toString());
			}
		}
		in.close();
	}
}
