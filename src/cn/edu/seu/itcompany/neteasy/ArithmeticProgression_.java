package cn.edu.seu.itcompany.neteasy;

import java.util.Arrays;
import java.util.Scanner;

/**[编程题] 等差数列
 * https://www.nowcoder.com/question/next?pid=6291726&qid=112725&tid=10233670
 * @Author personajian
 * @Date 2017/8/24 21:21
 */
public class ArithmeticProgression_ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int[] arr=new int[n];

            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }
            if(isArithmeticProgression(arr))
                System.out.println("Possible");
            else
                System.out.println("Impossible");

        }
        in.close();
    }

    private static boolean isArithmeticProgression(int[] arr){
        Arrays.sort(arr);

        int d=arr[1]-arr[0];

        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]+d!=arr[i+1])
                return false;
        }

        return true;
    }
}
