package cn.edu.seu.swordoffer;

public class MoreThanHalfNum {

    private static int half;

    private static int halfIndex;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        int result = new MoreThanHalfNum().MoreThanHalfNum_Solution(array);
        int count = 0;

        for (int i : array) {
            if (i == result)
                count++;
        }

        System.out.println(count >= array.length ? result : 0);


    }

    public int MoreThanHalfNum_Solution(int[] array) {

        int low = 0;
        int high = array.length - 1;

        halfIndex = (high - low) / 2;

        qucikSort(array, low, high);


        return half;


    }

    private void qucikSort(int[] array, int low, int high) {

        if (low >= high)
            return;
        int mid = partition(array, low, high);
        if (mid == halfIndex)
            half = array[mid];
        qucikSort(array, low, mid);
        qucikSort(array, mid + 1, high);

    }

    private int partition(int[] array, int low, int high) {

        int pivot = array[low];

        while (low < high) {
            while (low < high && array[high] >= pivot)
                high--;
            array[low] = array[high];
            while (low < high && array[low] <= pivot)
                low++;
            array[high] = array[low];
        }

        array[low] = pivot;

        return low;
    }


}