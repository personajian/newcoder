package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class ReverseSentence {

	public static String reverse(String s) {
		char[] ch = s.toCharArray();
		int len = s.length();
		char temp;
		for (int i = 0, j = len - 1; i < j; i++, j--) {
			temp = ch[i];
			ch[i] = ch[j];
			ch[j] = temp;
		}
		return new String(ch);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.nextLine();
			StringBuilder sb = new StringBuilder();
			String[] s = str.split(" ");
			for (int i = s.length - 1; i > 0; i--) {
				sb.append(s[i] + " ");
			}
			sb.append(s[0]);
			System.out.println(sb);

		}
		in.close();
	}
}
