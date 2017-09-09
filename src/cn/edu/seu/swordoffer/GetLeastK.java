package cn.edu.seu.swordoffer;

import java.util.ArrayList;

/**
 * 最小的K个数
 * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tqId=11182&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 算法思想：模拟快排的partition()切分函数，当mid==k-1是，左侧的数组就是最小的k个数
 *
 * @Author personajian
 * @Date 2017/9/7 16:15
 */
public class GetLeastK {

    public static void main(String[] args) {
        int[] input = {9, 2, 4, 0, 1, 7, 3, 6, 5, 8};
        int k = 4;
        ArrayList<Integer> result = GetLeastNumbers_Solution(input, k);

        for (int i : result)
            System.out.print(i + " ");

    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {

        if(k>input.length) return null;

        ArrayList<Integer> result = new ArrayList<>();

        quickSort(input, 0, input.length - 1, k);

        for (int i = 0; i < k; i++)
            result.add(input[i]);

        return result;
    }

    private static void quickSort(int[] a, int low, int high, int k) {
        if (high <= low)
            return;

        int mid = partiton(a, low, high);

        if (mid == k - 1)
            return;

        quickSort(a, low, mid, k);
        quickSort(a, mid + 1, high, k);
    }

    private static int partiton(int[] a, int low, int high) {
        int pivot = a[low];

        while (low < high) {
            while (low < high && a[high] >= pivot)
                high--;
            a[low] = a[high];
            while (low < high && a[low] <= pivot)
                low++;
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }
}