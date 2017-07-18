package cn.edu.seu.itcompany.huawei;

import java.util.Scanner;

public class SixteenMechanisms {
	
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			String sixteen=in.nextLine();
			int len=sixteen.length();
			int sixlen=len-2;
			String sixnum=sixteen.substring(2, len);
			char[] ch=sixnum.toCharArray();
			int result=0;
			
			for(int i=0;i<ch.length;i++) {
				switch (ch[i]) {
				case 'A':
					result+=10<<4*(sixlen-i-1);
					break;
				case 'B':
					result+=11<<4*(sixlen-i-1);
					break;
				case 'C':
					result+=12<<4*(sixlen-i-1);
					break;
				case 'D':
					result+=13<<4*(sixlen-i-1);
					break;
				case 'E':
					result+=14<<4*(sixlen-i-1);
					break;
				case 'F':
					result+=15<<4*(sixlen-i-1);
					break;
				default:
					result+= Character.getNumericValue(ch[i])<<4*(sixlen-i-1);
					break;
				}
			}
			System.out.println(result);
		}

	}
}
