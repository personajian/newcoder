package cn.edu.seu.swordoffer;

import java.util.ArrayList;

/**
 * @Author personajian
 * @Date 2017/9/17 21:10
 */
public class Permutation {

    public static void main(String[] args) {
        char[] chs = "abc".toCharArray();

        permutation(chs);
        permutation2(chs);

    }

    private static void permutation2(char[] chs) {

        ArrayList<ArrayList<Character>> paths = new ArrayList<>();


    }


    private static void permutation(char[] chs) {
        if (chs.length == 0)
            return;

        int begin = 0;
        permutation(chs, begin);
    }

    private static void permutation(char[] chs, int begin) {
        //递归出口：交换到字符串的最后一个字符就可以打印了
        if (begin == chs.length - 1) {
            for (char ch : chs)
                System.out.print(ch);
            System.out.println();

        } else {
            //将字符串分为：第一个字符串+后面的字符串
            for (int i = begin; i < chs.length; i++) {
                swap(chs, begin, i);
                //后面字符串的全排列
                permutation(chs, begin + 1);
                swap(chs, begin, i);
            }
        }


    }

    private static void swap(char[] chs, int begin, int i) {
        char temp = chs[begin];
        chs[begin] = chs[i];
        chs[i] = temp;
    }


}
