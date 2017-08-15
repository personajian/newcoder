package cn.edu.seu.itcompany.neteasy;

import java.util.Arrays;
import java.util.Scanner;

/**https://www.nowcoder.com/question/next?pid=6291726&qid=112727&tid=9863817
 * [编程题] 堆棋子
 * @Author personajian
 * @Date 2017/8/13 21:38
 */
public class PilePieces {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int[] x=new int[n];//i个棋子的x轴坐标
            int[] y=new int[n];//i个棋子的y轴坐标
            //int[][] xy=new int[n][n];


            for (int i = 0; i < n; i++) {
                x[i]=in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                y[i]=in.nextInt();
            }

            /*for (int index = 0; index < n-1; index++) {
                //int step= pilePieces(index,n,x,y);
                System.out.print(pilePieces(index,n,x,y)+" ");
            }*/
            int[] sum=pilePieces(n,x,y);

            for (int i = 0; i < n-1; i++) {
                System.out.print(sum[i]+" ");
            }
            System.out.println(sum[n-1]);


        }
        in.close();
    }

    private static int[] pilePieces(int n,int[] x,int[] y){

        int[][][] steps=new int[n][n][n];
        /*for (int i = 0; i <= index; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    steps[i][j][k]=-1;
                }
            }
        }*/

        //i个棋子的坐标(x,y)的所有排列组合是可能最终所有i个棋子所放的位置
        //先求
        for (int i = 0; i <n; i++) {
                for (int j = 0; j <n; j++) {//所有i个棋子的所有位置
                    for (int k = 0; k <n; k++) {
                    //坐标(i,j)到棋子1~n的移动步数
                    steps[i][j][k]= Math.abs(x[k] - x[i]) + Math.abs(y[k] - y[j]);
                    //steps[i][j][n]+= steps[i][j][k];
                }
                //坐标(i,j)到棋子1~n的移动步数排序
                Arrays.sort(steps[i][j]);
                }

        }
        //累计步数
        for (int i = 0; i <n; i++) {
            for (int j = 0; j <n; j++) {//所有i个棋子的所有位置
                for (int k = 1; k <n; k++) {
                    //坐标(i,j)到棋子1~n的移动步数
                    steps[i][j][k]+=steps[i][j][k-1] ;
                    //steps[i][j][n]+= steps[i][j][k];
                }
            }
        }

        int[] sum=new int[n];
        for (int i = 0; i < n; i++) {
            sum[i]=Integer.MAX_VALUE;
        }
        //取前i个棋子的最小操作数（步数）
        for (int k = 0; k <n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {//所有i个棋子的所有位置
                    if(steps[i][j][k]<sum[k]) sum[k]=steps[i][j][k];
                }
            }
        }

        return sum;


    }
}
