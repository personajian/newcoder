package cn.edu.seu.itcompany.envision;

/**之字形打印矩阵
 * @author personajian
 *
 */
public class S1 {
	public int[] printMatrix(int[][] mat, int n, int m) {
		int count = 0;
		int[] zigzag = new int[n * m];

		for (int i = 1; i <= n; i++) {
			if (i % 2 != 0) {

				for (int j = m; j >= 1; j--) {
					zigzag[count] = mat[i - 1][j - 1];
					count++;
				}

			} else {
				for (int j = 1; j <= m; j++) {
					zigzag[count] = mat[i - 1][j - 1];
					count++;
				}
			}
		}
		return zigzag;
	}
}
