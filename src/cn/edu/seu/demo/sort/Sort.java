package cn.edu.seu.demo.sort;


import java.util.Random;

/**
 * @Author personajian
 * @Date 2017/9/7 9:07
 */
public class Sort {

    public static void main(String[] args) {
        int m = 100;
        for (int k = 0; k < m; k++) {
            //int[] a={4,1,9,3,7,8,0,2,6,5};
            int[] a = constructArray(10);
            //原始序列
            for (int i : a)
                System.out.print(i + " ");

            System.out.println();
            //排序
            //quickSort(a, 0, a.length - 1);
            //bubbleSort(a,0,a.length-1);
            insertSort(a,0,a.length-1);

            //排序序列
            for (int i : a)
                System.out.print(i + " ");
            System.out.println();
            //是否有序？
            System.out.println(isSorted(a) ? "true" : "false");
            System.out.println();

        }

    }


    public static void quickSort(int[] a, int low, int high) {
        //递归基：一个元素
        if (high <= low)
            return;
        //核心函数：切分
        //int mid=partition(a,low,high);
        int mid = partitionRandom(a, low, high);

        quickSort(a, low, mid);
        quickSort(a, mid + 1, high);
    }

    private static int partition(int[] a, int low, int high) {
        //保存轴点，
        int pivtol = a[low];

        while (low < high) {
            while (low < high && a[high] >= pivtol)
                high--;
            a[low] = a[high];

            while (low < high && a[low] <= pivtol)
                low++;
            a[high] = a[low];
        }

        //a[high]=pivtol;
        a[low] = pivtol;
        return low;
    }

    private static int partitionRandom(int[] a, int low, int high) {

        //随机选取一个轴点[low,high]：Random#nextInt(bound)是[0,bound)左闭右开的区间。
        int random = new Random().nextInt(high - low + 1) + low;

        swap(a, random, low);

        //保存轴点
        int pivtol = a[low];

        while (low < high) {
            while (low < high && a[high] >= pivtol)
                high--;
            a[low] = a[high];

            while (low < high && a[low] <= pivtol)
                low++;
            a[high] = a[low];
        }

        a[high]=pivtol;
        //a[low] = pivtol;
        return low;
    }

    public static void bubbleSort(int[] a, int low, int high) {

        for (int i = low; i <= high; i++) {
            int min = i;
            for (int j = i; j <= high; j++) {
                if (a[min] > a[j])
                    min = j;
            }
            swap(a, i, min);
        }
    }

    /**插入排序
     * @Param
     * @Return
     */
    public static void insertSort(int[] a, int low, int high) {

        for (int i = low+1; i <= high; i++) {
            for (int j = i; j >low && a[j]<a[j-1] ; j--) {
                swap(a,j,j-1);
            }
        }
    }

    private static void swap(int[] a, int i, int min) {
        int temp = a[i];
        a[i] = a[min];
        a[min] = temp;
    }


    private static int[] constructArray(int n) {
        int[] a = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(100);
        }
        return a;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1])
                return false;
        }
        return true;
    }
}
