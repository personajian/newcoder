package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class Approximation {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			float d=in.nextFloat();
			float remain=d%1;
			float d1=d-remain;
			if(remain*2>=1) d1++;
			System.out.println((int)d1);
		}
		in.close();
	}
}
