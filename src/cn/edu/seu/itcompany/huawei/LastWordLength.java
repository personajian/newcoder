package cn.edu.seu.itcompany.huawei;

import java.util.Scanner;

/**
 * @author personajian
 *
 *计算字符串最后一个单词的长度，单词以空格隔开。
 */
public class LastWordLength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		String str= in.nextLine();
		int lenght=str.length();
		int lastspace=str.lastIndexOf(" ");
		//System.out.println(str.substring(lastspace+1, lenght));
		System.out.println(lenght-lastspace-1);

	}

}
