package cn.edu.seu.itcompany.jd;

import java.util.Scanner;

public class Main3 {

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
		
		
		int result=2*n*n-n+4*duishu(n);
		

		return result;
	}
	
	private static int duishu(int n) {
		int m=(int)Math.sqrt(n);
		
		int count=0;
		
		for(int i=2;i<=n;i++) {
			for(int j=2;j<=n;j++) {
				if((int)Math.pow(i, j)<=n)
					count++;
				/*else 
					break;*/
			}
		}
		
		return count;
	}

}
