package cn.edu.seu.itcompany.huawei;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num=in.nextInt();
			String[] str=new String[num];
			for(int i=0;i<num;i++) {
				str[i]=in.next();
			}
			
			Arrays.sort(str,new StrComparator());
			
			for(String s:str) {
				System.out.println(s);
			}
			
		}
		in.close();
	}

}

class StrComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		char[] ch1=o1.toCharArray();
		char[] ch2=o2.toCharArray();
		int len1=ch1.length;
		int len2=ch2.length;
		int min=len1<len2?len1:len2;
		
		for(int i=0;i<min;i++) {
			if(ch1[i]<ch2[i]) return -1;
			if(ch1[i]>ch2[i]) return 1;
		}
		if(len1<len2) return -1;
		if(len1>len2) return 1;
		return 0;
	}
	
}

