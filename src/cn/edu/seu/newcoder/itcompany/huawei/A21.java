package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class A21 {

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


