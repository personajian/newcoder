package cn.edu.seu.itcompany.envision;

import java.util.*;

/**矩阵相乘
 * @author personajian
 *
 */
public class S2_MatrixMultiply {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x=in.nextInt();
			int y=in.nextInt();
			int z=in.nextInt();
			
			int[][] matrixA=new int[x][y];
			int[][] matrixB=new int[y][z];
			
						
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) 
					matrixA[i][j]=in.nextInt();
			}
			
			for(int i=0;i<z;i++) {
				for(int j=0;j<y;j++) 
					matrixB[i][j]=in.nextInt();
			}
			
			int[][] result=matrixMultiply(matrixA,matrixB,x,y,z);
			
			for(int i=0;i<x;i++) {
				for(int j=0;j<z;j++) {
					if(j!=z-1)	System.out.print(result[i][j]+" ");
					else System.out.print(result[i][j]);
				}
				if(i!=x-1)	System.out.println();
						
			}
		}
		in.close();
	}

	private static int[][] matrixMultiply(int[][] matrixA, int[][] matrixB, int x, int y, int z) {
		int[][] matrixC=new int[x][z];
				
		for(int i=0;i<x;i++) {
			for(int j=0;j<z;j++) {
				int temp=0;
				for(int k=0;k<y;k++) {
					temp+=matrixA[i][k]*matrixB[k][j];
				}
				matrixC[i][j]=temp;
			}
		}
		
		return matrixC;
	}
}
