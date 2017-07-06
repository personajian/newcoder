package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

/**
 * @author personajian
 * [编程|200分] 电报翻译
	时间限制：3秒
	空间限制：32768K
	题目描述
	假设电报中用点（.）表示1，用中划线（-）表示0，点与中划线的序列，能够翻译成一个二进制数（可以看做无符号数）。将此二进制转换为整数后，通过一个映射表，可以将整数映射成一个英文字母。多个点、中划线序列间，用#隔开（多个连续的#号，算作一个#号），表示多个英文字母。
	当电报中没有点、中划线，只有#时，电报内容为空字符串。
	每个点、中划线序列，可以看做是无符号数。如果有点、中划线序列的二进制值超出如下映射表的范围，则输出”ERROR”。
	映射表：
	 
	请将输入的点、中划线序列集合，转换为英文字母集合。
	输入描述:由点（.）、中划线（-）、#集合构成的原始报文。
	
	输出描述:经过转换后生成的英文字符序列（英文区分大小写）
	二进制值超出如下映射表的范围，则输出”ERROR”
	输入例子:--.#.#-.-
	输出例子:GGR

 */
public class SpecialOffer21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		char[] ch= {'F','G','R','S','T','L','M','N','O','P','Q',
				'W','X','Y','Z','U','A','G','H','I','J','K',
				'B','C','D','E',
				'l','m','n','o',
				'p','i','j','k','f','g','h',
				'a','b','c','d',
				'e','q','r','w','x','y','z',
				's','t','u','v',};
		
		while (in.hasNext()) {

			String str=in.next().replace('.', '1').replace('-', '0');
			
			if(str==null||str.length()==0) continue;
			
			for(int i=0;i<str.length();i++) {
				char c=str.charAt(i);
				if(c!='1'&&c!='0'&&c!='#') {
					System.out.print("ERROR");
					continue;
				}
			}
			
			//System.out.println(str);
			String[] arr=str.split("#");
			
			int len=0;
			for(String s:arr) len+=s.length();
			
			if(len==0) {
				System.out.print("");
				//return;
			}else {
				for(String s:arr) {
					if(s.length()>0) {
						Integer number=Integer.valueOf(s,2);
						if(number>=0&&number<=51) {
							char alpha=ch[number];
							System.out.print(alpha);
						}else System.out.print("ERROR");
					}
				}
			}	
		}
		in.close();
	}

}


