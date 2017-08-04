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
            int result=numJumpStone(m,n);
        }
        in.close();
    }

    private static int numJumpStone(int m, int n){
        int differ=n-m;
        int step=0;
        for(;;){
            List<Integer> list=divisor(m);
            List<Integer> list1=new ArrayList<>(list.size());
            for(int div:list){
                list1.add(step+=div);
            }
            if(list1.contains(differ));
        }

    }


    private static List<Integer> divisor(int num){
        List<Integer> list=new ArrayList<>();
        for (int i = 2; i < (int)Math.sqrt(num); i++) {
            if(num%i==0){
                list.add(i);
                list.add(num/i);
            }
        }
        return list;
    }
}
