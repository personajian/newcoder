package cn.edu.seu.itcompany.huawei;

import java.util.Scanner;

public class MingRandom {
	
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			int[] arr=new int[1000];
			int num=in.nextInt();
			int[] random=new int[num];
			for(int i=0;i<num;i++) {
				random[i]=in.nextInt();
				arr[random[i]]++;	
			}
			
			for(int i=0;i<arr.length;i++) {
				if(arr[i]!=0) System.out.println(i);
			}
		}
		in.close();
	}

	
}
