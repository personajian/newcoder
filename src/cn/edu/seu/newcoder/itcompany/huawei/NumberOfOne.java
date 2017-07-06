package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class NumberOfOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num=in.nextInt();
			int remain=0;
			int count=0;
			while(num!=0) {
				remain=num%2;
				num=num/2;
				if(remain==1) count++;
			}
			System.out.println(count);
		}
		in.close();
	}

	
}


