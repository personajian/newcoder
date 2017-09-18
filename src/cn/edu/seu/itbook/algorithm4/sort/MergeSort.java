package cn.edu.seu.itbook.algorithm4.sort;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/9/5 20:42
 */
public class MergeSort extends AbstractSortion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int[] original = {3, 1, 2, 8, 6, 9, 5, 4, 0, 7};
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = original[i];
        }
        in.close();
        sort(a, 0, a.length - 1);
        System.out.println(isSorted(a));
        show(a);
    }


    public static void sort(Comparable[] a, int low, int high) {

        if (low < high) {
            int mid = (low + high) / 2;
            //前后子数组分别排序
            sort(a, low, mid);
            sort(a, mid + 1, high);
            //核心归并
            merge(a, low, mid, high);
        }
    }

    private static void merge(Comparable[] a, int low, int mid, int high) {

        Comparable[] b = new Comparable[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        int i, j, k;

        for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
            if (less(b[i], b[j]))
                a[k] = b[i++];
            else
                a[k] = b[j++];
        }
        while (i <= mid) a[k++] = b[i++];
        while (j <= high) a[k++] = b[j++];

    }
}