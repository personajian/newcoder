package cn.edu.seu.itcompany.neteasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**跳石阶
 * 小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
     这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
     例如：
     N = 4，M = 24：
     4->6->8->12->18->24
     于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
 * @Author personajian
 * @Date 2017/8/4 21:59
 */
public class JumpStone {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int m=in.nextInt();
            int n=in.nextInt();
            System.out.println(dp(m,n));
        }
        in.close();
    }

    private static int dp(int n, int m){
        if(m==n) return 0;

        int steps=m-n+1;
        int[] dp=new int[steps];//规划的量：到达每个位置需要的最小步数。
        dp[0]=0;//起点
        for(int i=1;i<steps;i++){
            dp[i]=Integer.MAX_VALUE;
        }

        for(int i=0;i<steps;i++){
            if(dp[i]==Integer.MAX_VALUE){
                dp[i]=0;
                continue;
            }

            List<Integer> divosr=divisor(i+n);
            for(int div:divosr){
                if(i+n+div<=m)
                    dp[i+div]=Math.min(dp[i+div],dp[i]+1);
            }
        }

        if (dp[steps - 1] == 0) return -1;
        else return dp[steps-1];

    }


    private static List<Integer> divisor(int num){
        List<Integer> list=new ArrayList<>();
        for (int i = 2; i <= (int)Math.sqrt(num); i++) {
            if(num%i==0){
                list.add(i);
                if(num/i!=i) list.add(num/i);
            }
        }
        return list;
    }
}
