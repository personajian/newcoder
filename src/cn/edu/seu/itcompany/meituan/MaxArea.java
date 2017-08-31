package cn.edu.seu.itcompany.meituan;

import java.util.Scanner;

/**https://www.nowcoder.com/questionTerminal/b0fbb688d01a4f2c8c17e5efd85d5824?commentTags=C%2FC%2B%2B
 * [编程题]最大矩形面积
 * @Author personajian
 * @Date 2017/8/31 15:42
 */
public class MaxArea {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n=in.nextInt();
            int[] arr=new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }

            System.out.println(maxArea(arr,n));
        }
    }

    private static int maxArea(int[] arr ,int n ){

        int[] max=new int[n];

        return max(arr,max,n);
    }


    private static int max(int[] arr,int[] max,int n){

        int maxArea=Integer.MIN_VALUE;


        for (int i = 0; i < n; i++) {
            int low=i;
            int high=i;
            int count=1;
            while(low>=1){
                if(arr[low-1]>=arr[i]) {
                    count++;
                    low--;
                }else
                    break;
            }
            while(high<=n-2){
                if(arr[high+1]>=arr[i]) {
                    count++;
                    high++;
                }else
                    break;
            }
            max[i]=count*arr[i];

            if(maxArea<max[i])
                maxArea=max[i];
        }

        return maxArea;
    }
}
