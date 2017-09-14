package cn.edu.seu.swordoffer;

/**
 * 连续子数组最大和
 * https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484
 *
 * @Param
 * @Return
 */
public class SumOfSubArray {

    public static void main(String[] args) {
        int[] array = {6, -3, -2, 7, -15, 1, 2, 2};
        int sum = new SumOfSubArray().FindGreatestSumOfSubArray(array);
        System.out.println(sum);
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int p1 = 0, p2 = array.length - 1;

        int sum = summary(array, p1, p2);

        int max = sum;

        while (p1 < p2) {
            if (array[p1] < array[p2]) {
                sum -= array[p1];

                if (max < sum)
                    max = sum;

                p1++;
            } else {
                sum -= array[p2];
                if (max < sum)
                    max = sum;
                p2--;
            }
        }
        return max;

    }

    private int summary(int[] a, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++)
            sum += a[k];
        return sum;
    }
}