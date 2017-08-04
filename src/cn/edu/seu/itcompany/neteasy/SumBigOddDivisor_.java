package cn.edu.seu.itcompany.neteasy;

import java.util.*;

/**效率太低，太慢了！！
 * @Author personajian
 * @Date 2017/8/4 23:12
 */
public class SumBigOddDivisor_ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            long n=in.nextLong();
            System.out.println(sumBigOddDivisor(n));
            //System.out.println(bigOddDivisor(n));
        }
        in.close();
    }

    private  static long sumBigOddDivisor(long n){
        long sum=0;
        long temp=0;
        for(long i=1;i<=n;i++){
            temp=i;
            while((temp&1)==0)
                temp=temp>>1;
            sum+=bigOddDivisor(i);
        }
        return sum;

    }

    private static long bigOddDivisor(long n){

        while((n&1)==0){
            n=n>>1;
        }

        for(long i=n;i>(long)Math.sqrt(n);i--){
            if(n%i==0&&i%2==1) return i;
        }
        return 1;
    }

}
