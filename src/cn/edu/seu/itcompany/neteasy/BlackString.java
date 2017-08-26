package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**黑暗字符串的可能有多少种？
 * 一个只包含'A'、'B'和'C'的字符串，如果存在某一段长度为3的连续子串中恰好'A'、'B'和'C'各有一个，那么这个字符串就是纯净的，否则这个字符串就是暗黑的。例如：
 BAACAACCBAAA 连续子串"CBA"中包含了'A','B','C'各一个，所以是纯净的字符串
 AABBCCAABB 不存在一个长度为3的连续子串包含'A','B','C',所以是暗黑的字符串
 你的任务就是计算出长度为n的字符串(只包含'A'、'B'和'C')，有多少个是暗黑的字符串。

 *解析：长度n的黑暗字符串个数是由上一个阶段的某些状态得到的：Same表示后两位字母相同，Differ表示后两位字母不同。
 * dp(n)=3*Same(n-1)+2*Differ(n-1)
 * dp(n)=2*(Same(n-1)+Differ(n-1))+Same(n-1)
 * dp(n)=2*dp(n-1)+Same(n-1)
 * dp(n)=2*dp(n-1)+dp(n-2)
 *  @Author personajian
 * @Date 2017/8/4 22:29
 */
public class BlackString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            long n=in.nextLong();
            long count=dp(n);
            System.out.println(count);
        }
        in.close();
    }

    public static long dp(long n){

        if(n==1) return 3;
        if(n==2) return 9;
        return 2*dp(n-1)+dp(n-2);
    }
}
