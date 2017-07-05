package cn.edu.seu.leetcode.dp;

/**
 * @author personajian
 * question:求解最长公共子序列LCS
 */
public class Lcs {

	/**
	 * 动态规划求解LCS
	 * @param x是一个字符串序列
	 * @param y是另一个字符串序列
	 * @param b是对应计算c时所选择的子问题最优解
	 * @param c是保存了x和y的LCS长度
	 */
	public static void lcsLength(char[] x,char[] y,int[][] b,int[][] c) {
		int m=x.length;
		int n=y.length;
		for(int i=1;i<m;i++) c[i][0]=0;
		for(int j=0;j<n;j++) c[0][j]=0;
		for (int i = 1; i <=m; i++) {
			for (int j = 1; j <= n; j++) {
				if(x[i-1]==y[j-1]){//字符串下标从0开始
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=-2;
				}else if(c[i-1][j]>=c[i][j-1]){
					c[i][j]=c[i-1][j];
					b[i][j]=1;
				}else{
					c[i][j]=c[i][j-1];
					b[i][j]=-1;
				}
			}
		}
				
	}
	
	/**
	 * 打印最长公共子序列
	 * @param b
	 * @param x
	 * @param i
	 * @param j
	 */
	public static void printLcs(int[][] b,char[] x,int i,int j) {
		if(i==0||j==0) return;
		if(b[i][j]==-2){
			printLcs(b, x, i-1, j-1);
			System.out.print(x[i-1]);//字符串下标从0开始
		}else if(b[i][j]==1) printLcs(b, x, i-1, j); 
		else printLcs(b, x, i, j-1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] x={'A','B','C','B','D','A','B'};
		char[] y={'B','D','C','A','B','A'};
		int i=x.length;
		int j=y.length;
		int[][] b=new int[i+1][j+1];
		int[][] c=new int[i+1][j+1];
		lcsLength(x, y, b, c);
		printLcs(b, x, i, j);
	}

}
