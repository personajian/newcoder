package cn.edu.seu.itcompany.zhaohang;

import java.util.Scanner;


/**
 * Created by liunian on 2017/9/13.
 */
public class main1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			if (n <= 2)
				System.out.print(true);
			else {
				int[] arr = new int[n];
				for (int i = 0; i < n; i++) {
					arr[i] = in.nextInt();
				}
				System.out.println(solve(arr,n));

			}

		}
		in.close();



	}

	private static String solve(int[] arr, int n) {

		int[] other = new int[n];
		
		for (int i = 0; i < n; i++) {
			other[i] = arr[n - i - 1];

		}

		int[] A = new int[n];
		int[] B = new int[n];
		A[0] = other[0];
		B[0] = -other[0];
		A[1] = other[0] + other[1];
		B[1] = -A[1];
		for (int i = 2; i < n; i++) {
			A[i] = Math.max(other[i] + B[i - 1], other[i - 1] + other[i] + B[i - 2]);
			B[i] = Math.max(-other[i] + A[i - 1], -(other[i - 1] + other[i]) + A[i - 2]);
		}
		if (A[n - 1] > 0)
			return "true";
		else
			return "false";
	}
}
