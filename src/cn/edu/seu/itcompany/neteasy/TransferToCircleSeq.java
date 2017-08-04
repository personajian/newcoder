package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**回文序列
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
     {1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列,
     {1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
     现在给出一个数字序列，允许使用一种转换操作：
     选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数之前的位置(只插入一个和)。
     现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。
 * @Author personajian
 * @Date 2017/8/2 21:43
 */
public class TransferToCircleSeq {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int m=in.nextInt();
            int[] array=new int[m];
            for(int i=0;i<m;i++){
                array[i]=in.nextInt();
            }

            int count = TransferToCircleSeq(array);
            System.out.println(count);
        }
        in.close();
    }

    private static int TransferToCircleSeq(int[] array){
        int m=array.length;
        int count=0;

        int low=0,high=m-1;

        while(low<high){
            //if(isCircleSeq(array,low,high)) return count;

            if(array[low]<array[high]){
                array[low+1]+=array[low];
                low++;
                count++;
            }else if(array[low]>array[high]){
                array[high-1]+=array[high];
                high--;
                count++;
            }else{
                low++;
                high--;
            }
        }
        return count;
    }

    /*private static boolean isCircleSeq(int[] array,int low,int high){
        for(;low<high;low++,high--){
            if(array[low]!=array[high]){
                return false;
            }
        }
        return true;
    }*/
}
