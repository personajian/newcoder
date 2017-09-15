package cn.edu.seu.itcompany.aqiyi;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int result = solve(n, m);
			System.out.println(result);
		}
		in.close();
	}

	private static int solve(int n, int m) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				/*
				 * if((i*j)%3==0||(i*j)%3==1) count++;
				 */

				if (isRemain(i * j))
					count++;

				/*
				 * if(i==j) { count++; }else if(){ if(isRemain(i) && isRemain(j)) { count++; } }
				 */

			}
		}

		return count;
	}

	private static boolean isRemain(int a) {
		double temp = Math.sqrt(a);
		int t = (int) temp;
		if (temp - t == 0)
			return true;
		else
			return false;
	}

	private static boolean isRemain1(int a) {
		for (int i = 1; a > 0; i += 2) {
			a -= i;
		}
		if (a == 0)
			return true;
		else
			return false;
	}

	private static boolean isRemain2(int num) {
		int i, sum;
		for (i = 1, sum = 0; sum < num; ++i)
			sum += (2 * i - 1);
		if (sum == num)
			return false;
		return true;
	}
	
	private static boolean isRemain3(int n)
	{   
	    int i = 1;
	    for(;n>0;i+=2) n-=i;   
	     
	    return 0==n;   
	}
}
