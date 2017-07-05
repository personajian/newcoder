package cn.edu.seu.itbook.algorithm4.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author personajian
 * 快速排序
 */
public class Quick extends AbstractSortion{

	public static void sort(Comparable[] a) {
		/*List list=Arrays.asList(a);
		Collections.shuffle(list);
		a=(Comparable[]) list.toArray();*/
		sort(a,0,a.length-1);
	}


	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int j=partition(a,lo,hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	/**切分方法
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i=lo,j=hi+1;
		Comparable v=a[lo];
		while (true) {
			//从数组的左端开始向右扫描直到找到一个大于等于v的元素，
			//再从数组的右端开始向左扫描直到找到一个小于等于v的元素。
			while(less(a[++i], v)) if(i==hi) break;
			while(less(v, a[--j])) if(j==lo) break;
			if(i>=j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
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
