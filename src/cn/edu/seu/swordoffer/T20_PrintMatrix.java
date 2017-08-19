package cn.edu.seu.swordoffer;

/**
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵：
 * 1  2   3  4
 * 5  6   7  8
 * 9  10 11 12
 * 13 14 15 16
 * 则依次打印出数字
 * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * <p>
 * 算法思想：每一圈打印的起点肯定是(0,0),(1,1),(2,2)...
 * matrix[m][n]
 * 关键在于下标的控制！！！
 *
 * @Author personajian
 * @Date 2017/8/19 16:26
 */

import java.util.ArrayList;

public class T20_PrintMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayList<Integer> list = printMatrix(matrix);
        for (int i : list)
            System.out.print(i + " ");

    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {

        //行数
        int rows = matrix.length;
        //列数
        int columns = matrix[0].length;
        //打印数组
        ArrayList<Integer> list = new ArrayList<>();

        //从最外圈开始打印,开始的点总是(0,0),(1,1),(2,2)...
        int start = 0;
        //int m0=0;
        //int n0=n-1;

        while (rows > start * 2 && columns > start * 2) {
            //等同于每一圈的打印

            int endX = columns - 1 - start;
            int endY = rows - 1 - start;

            for (int i = start; i <= endX; i++)
                list.add(matrix[start][i]);


            if (start < endY) {
                for (int i = start + 1; i <= endY; i++)
                    list.add(matrix[i][endX]);
            }

            if (start < endX && start < endY) {
                for (int i = endX - 1; i >= start; i--)
                    list.add(matrix[endY][i]);
            }

            if (start < endX && start < endY - 1) {
                for (int i = endY - 1; i >= start + 1; i--)
                    list.add(matrix[i][start]);
            }

            start++;

        }


        return list;
    }
}