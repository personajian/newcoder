package cn.edu.seu.itbook.algorithm4.sort;

import java.util.Scanner;

/**
 * @author personajian
 * 插入排序
 */
public class InsertSort extends AbstractSortion {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int[] original = {3, 1, 2, 8, 6, 9, 5, 4, 0, 7};
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = original[i];
        }
        in.close();
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

}
