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
public class Stagger01_ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.next();
            //str = "0101010";
            System.out.println(stagger(str));
        }
        in.close();
    }

    private static int stagger(String str) {

        int len = str.length();

        char[] chs = str.toCharArray();

        int[] arr = new int[len];

        for (int i = 0; i < chs.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(chs[i]));
        }

        int max = Integer.MIN_VALUE;
        int p1 = 0;
        int p2 = p1;

        while (true) {
            if (differ(arr, p2))
                p2++;
            else {
                p2++;
                p1 = p2;
            }

            if (max < p2 - p1 + 1)
                max = p2 - p1 + 1;

            if(p2==len-1)
                return max;
        }

    }

    private static boolean differ(int[] arr,int p){
        if(arr[p]!=arr[p+1])
            return true;
        else
            return false;
    }
}
