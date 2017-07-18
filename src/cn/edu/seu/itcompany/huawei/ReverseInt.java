package cn.edu.seu.itcompany.huawei;

import java.util.Scanner;

public class ReverseInt {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			Integer num=in.nextInt();
			String str=num.toString();
			
			for(int i=str.length()-1;i>=0;i--) {
				System.out.print(str.substring(i, i+1));
			}
		}
		in.close();
	}
}
