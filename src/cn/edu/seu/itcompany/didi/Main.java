package cn.edu.seu.itcompany.didi;
import java.util.Scanner;

public class Main {

	
	private static Scanner in;
	private static int n=0;
	private static int[] arr;
	private static int lowIndex = -1;
	private static int highIndex = -1;
	private static int temp = -1;
	private static int result=0;
	
	public static void main(String[] args) {
		in=new Scanner(System.in);
        String[] s = in.nextLine().split(" ");
        n=s.length;
        arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(s[i]);
        }
        System.out.println(sumSubstring(arr,n));
	}

	private static int sumSubstring(int[] arr,int n) {
		
		int bigger = Integer.MIN_VALUE;
		int summary = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; i++) {
			
			if (summary <= 0){
				summary = arr[i];
				temp = i;
			}else  {
				summary += arr[i];
			} 
			
			if (summary > bigger) {
				lowIndex = temp;
				bigger = summary;
				highIndex = i;
			}
		}
		
		for (int j = lowIndex; j <= highIndex; j++) {
			result+=arr[j];
		}
		
		return result;
	}
}
