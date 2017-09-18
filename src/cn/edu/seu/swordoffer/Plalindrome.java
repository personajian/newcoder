package cn.edu.seu.swordoffer;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/9/17 22:50
 */
public class Plalindrome {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        while(in.hasNext()){
            String str=in.next();
            System.out.println(isPlalindrome(str));
        }
    }

    private static String isPlalindrome(String str) {
        int start=0;
        int end=str.length()-1;

        while(start<=end){
            if(str.charAt(start)==str.charAt(end)){
                start++;
                end--;
            }
            else
                return "NO!";
        }

        return "YES!";

    }
}
