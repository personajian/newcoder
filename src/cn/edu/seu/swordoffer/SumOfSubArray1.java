package cn.edu.seu.swordoffer;

/**
 * 连续子数组最大和
 * https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484
 *
 * @Param
 * @Return
 */
public class SumOfSubArray1 {

    public static void main(String[] args) {
        int[] array = {6, -3, -2, 7, -15, 1, 2, 2};
        int sum = new SumOfSubArray1().FindGreatestSumOfSubArray(array);
        System.out.println(sum);
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int sum = array[0];

        int max = sum;

        for (int i = 1; i < array.length; i++) {
            if (sum < 0) {
                sum = array[i];
            }else
                sum += array[i];

            if (max < sum)
                max = sum;
        }
        return max;
    }
}