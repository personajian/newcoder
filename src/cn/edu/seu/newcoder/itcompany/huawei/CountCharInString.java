package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

/**
 * @author personajian
 *
 *写出一个程序，接受一个有字母和数字以及空格组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 */
public class CountCharInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			String str= in.nextLine().toLowerCase();
			String character=in.nextLine().toLowerCase();
			int count=0;
			int fromIndex=0;
			while(str.indexOf(character, fromIndex)!=-1){
				count++;
				fromIndex=str.indexOf(character, fromIndex)+1;
			}
			System.out.println(count);
		}
		in.close();
	}

}
