package cn.edu.seu.itcompany.meituan.main2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/8/24 21:23
 */
public class Main {

	private static boolean flag = false;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		isYes(arr, n);
		if (flag)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	private static void isYes(int[] arr, int n) {

		Arrays.sort(arr);

		int max = arr[n - 1];
		int sum = 0;
		for (int i = 0; i < n - 1; i++) {
			sum += arr[i];
		}
		if (max <= sum)
			flag = true;
	}
}
