package cn.edu.seu.itbook.algorithm4.sort;

import java.util.Scanner;

/**
 * @author personajian
 * 希尔排序
 */
public class Shell extends AbstractSortion{

	public static void sort(Comparable[] a) {
		int N=a.length;
		int h=1;
		while(h<N/3) h=3*h+1;
		
		while(h>=1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >=h&&less(a[j],a[j-h]); j-=h) {
					exch(a,j,j-h);
				}
			}
			h=h/3;
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
