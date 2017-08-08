package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/8/7 21:25
 */
public class DoubleCore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int[] element=new int[n];
            for (int i = 0; i < n; i++) {
                element[i]=in.nextInt();
            }


        }
        in.close();
    }
}
