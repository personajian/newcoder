package cn.edu.seu;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/8/24 21:23
 */
public class _Scanner {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            System.out.println(n);
        }
        in.close();
    }
}
