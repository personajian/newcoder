package cn.edu.seu.itcompany.alibaba;

/**阿里2018年内推算法题；打靶
 * @Author personajian
 * @Date 2017/8/20 15:17
 */

import java.util.Arrays;
import java.util.Scanner;

public class HitTarget {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int score = 0;
            int pre = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] == 0) {
                    score += maxScore(Arrays.copyOfRange(arr, pre, i));
                    pre = i + 1;
                }
            }
            score += maxScore(Arrays.copyOfRange(arr, pre, n));
            System.out.println(score);
        }
        in.close();

    }

    private static int maxScore(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len + 2][len + 2];
        int[][] visited = new int[len + 2][len + 2];
        int[] numsPlus = new int[len + 2];

        for (int i = 1; i < len + 1; i++) {
            numsPlus[i] = arr[i - 1];
        }
        numsPlus[0] = 1;
        numsPlus[len + 1] = 1;

        int result = search(dp, visited, numsPlus, 1, len);

        return result;
    }

    private static int search(int[][] dp, int[][] visit, int[] numsPlus, int start, int end) {
        if (visit[start][end] == 1) {
            return dp[start][end];
        }
        int res = 0;
        for (int i = start; i <= end; i++) {
            int mid = numsPlus[start - 1] * numsPlus[i] * numsPlus[end + 1];
            int right = search(dp, visit, numsPlus, start, i - 1);
            int left = search(dp, visit, numsPlus, i + 1, end);
            res = Math.max(res, mid + right + left);
        }
        visit[start][end] = 1;
        dp[start][end] = res;

        return res;
    }
}
