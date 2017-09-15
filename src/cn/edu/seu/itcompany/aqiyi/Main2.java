package cn.edu.seu.itcompany.aqiyi;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		while(in.hasNext()) {
			int n=in.nextInt();
			int m=in.nextInt();
			int result=solve(n,m);
			System.out.println(result);
		}
	}

	private static int solve(int n, int m) {
		int count=0;

		int countn=list(n);
		int countm=list(m);
		
		int temp=(n>m)?m:n;
		int ctemp=(countn>countm)?countm:countn;
		
		count=countn*countm+temp-ctemp;		
		
		return count;
	}

	
	private static boolean isRemain(int a) {
		double temp=Math.sqrt(a);
		int t=(int)temp;
		if(temp-t==0)
			return true;
		else
			return false;
	}
	
	private static int list(int a) {
		int count=0;
		for (int i = 1; i <= a; i++) {
			if(isRemain(i))
				count++;
		}
		return count;
	}
}
