    package cn.edu.seu.itcompany.meituan;

    import java.util.Scanner;

    /**https://www.nowcoder.com/questionTerminal/c0503ca0a12d4256af33fce2712d7b24?commentTags=C%2FC%2B%2B
     * [编程题]硬币表示
     * 动态规划！
     * dp[i]：组成i分的表示方法个数
     * dp[i]=dp[i-1]+dp[i-5]+dp[i-10]+dp[i-25]
     * @Author personajian
     * @Date 2017/8/31 16:07
     */
    public class CoinCounter {

        private static final int MOD= 1000000007;

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while(in.hasNext()){
                int n=in.nextInt();
                System.out.println(coinCounter(n));
            }
        }

        /**动态规划的迭代解法
         * @Param
         * @Return
         */
        private static int coinCounter(int n){

            int[] coins={1,5,10,25};

            int[] dp=new int[n+1];

            dp[0] =1;

            for (int i = 0; i < 4; i++) {
                for(int j=coins[i];j<=n;j++){
                    dp[j]+=dp[j-coins[i]];
                    dp[j]=dp[j]>MOD?dp[j]%MOD:dp[j];
                }
            }

            return dp[n];
        }
    }


