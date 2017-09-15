package cn.edu.seu.itcompany.didi;

import java.util.Scanner;

public class Main1 {

	
	private static int result=0;
	private static int n=0;
	private static int[] arr;
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		while(in.hasNext()){
			String[] s = in.nextLine().split(" ");
			n=s.length;
			arr=new int[n];
			for (int i = 0; i < n; i++) {
				arr[i]=Integer.parseInt(s[i]);
			}
			System.out.println(sumSubstring(arr));
		}
		in.close();
	}

	private static int sumSubstring(int[] arr) {

		int lowIndex = -1;
		int highIndex = -1;
		int temp = -1;
		
		
		int max = Integer.MIN_VALUE;
		int summary = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			if (summary > 0) {
				summary += arr[i];
			} else {
				summary = arr[i];
				temp = i;
			}
			if (summary > max) {
				lowIndex = temp;
				max = summary;
				highIndex = i;
			}
		}
		
		for (int j = lowIndex; j <= highIndex; j++) {
			result+=arr[j];
		}
		return result;
	}
}
