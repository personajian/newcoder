package cn.edu.seu.itcompany.meituan;

/**https://www.nowcoder.com/questionTerminal/c0503ca0a12d4256af33fce2712d7b24?commentTags=C%2FC%2B%2B
 * [编程题]硬币表示
 * 动态规划！
 * dp[i]：组成i分的表示方法个数
 * dp[i]=dp[i-1]+dp[i-5]+dp[i-10]+dp[i-25]
 * @Author personajian
 * @Date 2017/8/31 16:07
 */

public class Coins {

    private static final long MOD= 1000000007;

    public int countWays(int n) {
        return (int)coinCounter(n);
    }

    private long coinCounter(int n){
        long[] dp=new long[n+1];

        dp[1] =1;

        for(int i=2;i<n+1;i++){
            int temp=0;
            if(i-25>=0)
                temp+=dp[i-25];
            if(i-10>=0)
                temp+=dp[i-10];
            if(i-5>=0)
                temp+=dp[i-5];
            if(i-1>=0)
                temp+=dp[i-1];

            dp[i]=temp>MOD?temp%MOD:temp;
        }

        return dp[n];
    }
}



