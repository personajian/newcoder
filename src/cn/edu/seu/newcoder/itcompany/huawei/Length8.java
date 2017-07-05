package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Length8 {
	
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			List<String> list=new LinkedList<String>();
			for(int line=0;line<2;line++) {
				String str=in.next();
				//System.out.println(str);
				int len=str.length();
				int num=len/8;
				int remain=len%8;
				if(num==0) {
					String strRemain = str.substring(8*num,len);
					for(int i=0;i<8-remain;i++) {
						strRemain+="0";
					}
					list.add(strRemain);			
				}else {
					for(int i=0;i<num;i++) {
						list.add(str.substring(8*i, 8*(i+1)));
					}
					if(remain!=0) {
						String strRemain = str.substring(8*num,len);
						for(int i=0;i<8-remain;i++) {
							strRemain+="0";
						}
						list.add(strRemain);
					}				
				}
			}
			for(String string:list) {
				System.out.println(string);
			}
		}
		//in.close();
	}
}
