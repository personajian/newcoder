package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**https://www.nowcoder.com/questionTerminal/6ffdd7e4197c403e88c6a8aa3e7a332a
 * @Author personajian
 * @Date 2017/8/11 10:17
 */
public class EndZero {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            System.out.println(endZero(n));
        }
        in.close();
    }

    private static int endZero(int n){
        int count=0;
        while(n!=0){
            count+=n/5;
            n/=5;
        }
        return count;
    }
}
