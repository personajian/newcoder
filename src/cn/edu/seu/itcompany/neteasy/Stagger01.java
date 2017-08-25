package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**https://www.nowcoder.com/question/next?pid=6291726&qid=112729&tid=10233670
 * [编程题] 交错01串
 时间限制：1秒
 空间限制：32768K
 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。例如: "1","10101","0101010"都是交错01串。
 小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。
 * @Author personajian
 * @Date 2017/8/24 20:07
 */
public class Stagger01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str=in.next();
            str="0101010";
            System.out.println(stagger(str));
        }
        in.close();
    }

    private static int stagger(String str){

        int len=str.length();

        char[] chs=str.toCharArray();

        int[] arr=new int[len];

        int[][] matrix=new int[len][len];

        for (int i = 0; i <chs.length ; i++) {
            arr[i]=Integer.parseInt(String.valueOf(chs[i]));
            if(arr[i]==0)
                arr[i]=-1;
        }

        int max=Integer.MIN_VALUE;
        int flagi=0;
        int flagj=0;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                matrix[i][j]=sum(arr,i,j);
                if(matrix[i][j]==0){
                    if(max<j-i+1) {
                        max = j - i+1;
                        flagj=j;
                        flagi=i;
                    }
                }
            }
        }

        /*System.out.println(flagi);
        System.out.println(flagj);
        System.out.println(max);*/

        if(flagj!=len-1){
            if(arr[flagj]!=arr[flagj+1])
                max++;
        }




        return max;
    }

    private static int sum(int[] arr,int i,int j){
        int sum=0;
        for (int k = i; k <= j; k++) {
            sum+=arr[k];
        }
        return sum;
    }
}
