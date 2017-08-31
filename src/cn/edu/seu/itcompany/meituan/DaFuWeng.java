package cn.edu.seu.itcompany.meituan;

import java.util.Scanner;

/**https://www.nowcoder.com/questionTerminal/d66442d58616473dafb91b168d8ebd4d
 * [编程题]大富翁游戏
 * @Author personajian
 * @Date 2017/8/31 15:18
 */
public class DaFuWeng {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n=in.nextInt();
            System.out.println(steps(n));
        }
    }

    private static int steps(int n){
        if(n==1)
            return 1;
        else{
            return 2*steps(n-1);
        }
    }
}
