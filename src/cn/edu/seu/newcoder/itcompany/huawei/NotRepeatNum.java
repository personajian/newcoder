package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class NotRepeatNum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			Integer num=in.nextInt();
			String str=num.toString();
			boolean[] visited=new boolean[10];
			int len=str.length();
			int[] arr=new int[len];
			
			for(int i=0;i<len;i++) {
			   Character ch = str.charAt(i);
			   arr[len-i-1]=Integer.parseInt(ch.toString());
			}
			
			for(int i=0;i<len;i++) {
				if(!visited[arr[i]]) {
					System.out.print(arr[i]);
					visited[arr[i]]=true;
				}
			}
		}
		in.close();
	}
}
