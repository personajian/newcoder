package cn.edu.seu.itbook.algorithm4.sort;

import java.util.Scanner;

/**
 * @author personajian
 * 选择排序。
 */
public class SelectSort extends AbstractSortion{

	public static void sort(Comparable[] a) {
		int N=a.length;
		for (int i = 0; i < N; i++) {
			int min=i;
			for(int j=i+1;j<N;j++)
				if(less(a[j], a[min])) min=j;
			exch(a, i, min);	
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		int[] original = {3,1,2,8,6,9,5,4,0,7};
		Integer[] a=new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i]=original[i];
		}
		in.close();
		sort(a);
		System.out.println(isSorted(a));
		show(a);
	}

}
