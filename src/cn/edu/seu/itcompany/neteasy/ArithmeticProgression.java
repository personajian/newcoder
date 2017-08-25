package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**[编程题] 等差数列
 * https://www.nowcoder.com/question/next?pid=6291726&qid=112725&tid=10233670
 * @Author personajian
 * @Date 2017/8/24 21:21
 */
public class ArithmeticProgression {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int[] arr=new int[n];

            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }
            //System.out.println(n);
            if(isArithmeticProgression(arr))
                System.out.println("Possible");
            else
                System.out.println("Impossible");

        }
        in.close();
    }

    private static boolean isArithmeticProgression(int[] arr){
        boolean flag=false;
        int len=arr.length;
        int d=0;
        int min1=arr[0];
        int min2=arr[1];
        int sum=0;

        for (int i = 0; i < len; i++) {
            if(arr[i]<min1 && arr[i]<min2 && min1<min2){
                min1=arr[i];
                min2=min1;
            }
            sum+=arr[i];
        }

        d=min2-min1;

        int differ=sum-len*min1;

        if(differ%d==0){
            if(differ/d*2==len*(len-1))
                flag=true;
        }

        return flag;
    }
}
