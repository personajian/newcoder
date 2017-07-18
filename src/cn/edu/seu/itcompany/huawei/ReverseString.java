package cn.edu.seu.itcompany.huawei;

import java.util.Scanner;

public class ReverseString {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str=in.next();
	
			for(int i=str.length()-1;i>=0;i--) {
				System.out.print(str.substring(i, i+1));
			}
		}
		in.close();
	}
}
