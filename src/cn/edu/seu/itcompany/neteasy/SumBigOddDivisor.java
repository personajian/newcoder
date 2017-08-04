package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**最大奇约数之和
 * 小易是一个数论爱好者，并且对于一个数的奇数约数十分感兴趣。一天小易遇到这样一个问题： 定义函数f(x)为x最大的奇数约数，x为正整数。 例如:f(44) = 11.
 现在给出一个N，需要求出 f(1) + f(2) + f(3).......f(N)
 例如： N = 7
 f(1) + f(2) + f(3) + f(4) + f(5) + f(6) + f(7) = 1 + 1 + 3 + 1 + 5 + 3 + 7 = 21
 小易计算这个问题遇到了困难，需要你来设计一个算法帮助他。
 * @Author personajian
 * @Date 2017/8/5 0:21
 */
public class SumBigOddDivisor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            long n=in.nextLong();
            System.out.println(sumBigOddDivisor(n));
            //System.out.println(bigOddDivisor(n));
        }
        in.close();
    }

    private static long sumBigOddDivisor(long n){
        if(n==0) return 0;
        if((n&1)==0){//n为偶数时
           return sumBigOddDivisor(n>>1)+n*n/4;
        }else{
            return sumBigOddDivisor(n-1)+n;
        }

    }
}
