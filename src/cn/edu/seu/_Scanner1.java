package cn.edu.seu;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/8/24 21:23
 */
public class _Scanner1 {

    private static int n, m, result, temp;
    private static int row, column;
    private static int[][] matrix;
    private static boolean flag = false;
    private static String str = "";
    private static LinkedList<Integer> queue = new LinkedList<>();
    private static LinkedList<Integer> stack = new LinkedList<>();
    private static LinkedList<Integer> list = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt();
        column = in.nextInt();
        matrix = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        solute();
    }

    private static void solute() {

    }
}
