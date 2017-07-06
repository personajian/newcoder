package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class BrotherWord {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num=in.nextInt();
			String[] str=new String[num];
			String[] clone=new String[num];
			boolean[] flag=new boolean[num];
			for(int i=0;i<num;i++) {
				str[i]=in.next();
				clone[i]=resortString(str[i]);
			}
			
			int same=0,brother=0,count=0;
			String goal=in.next();
			int outNum=in.nextInt();
			for(int i=0;i<num;i++) {
				if(resortString(goal).equals(clone[i])) {
					brother++;
					flag[i]=true;
				}
			}
			for(int i=0;i<num;i++) {
				if (goal.equals(str[i])) {
					same++;
					flag[i]=false;
				}
			}

			System.out.print(brother-same);
			for(int i=0;i<str.length;i++) {
				if(flag[i]) {
					count++;
					if(count==outNum) {
						System.out.println();
						System.out.print(str[i]);
					}
				}
			}
			
			
		}
		in.close();
	}

	public static String resortString(String str) {
		char[] ch=str.toCharArray();

		Arrays.sort(ch);
		
		return new String(ch);
	}

}
