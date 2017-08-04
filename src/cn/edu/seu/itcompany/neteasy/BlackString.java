package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**黑暗字符串的可能有多少种？
 * 一个只包含'A'、'B'和'C'的字符串，如果存在某一段长度为3的连续子串中恰好'A'、'B'和'C'各有一个，那么这个字符串就是纯净的，否则这个字符串就是暗黑的。例如：
 BAACAACCBAAA 连续子串"CBA"中包含了'A','B','C'各一个，所以是纯净的字符串
 AABBCCAABB 不存在一个长度为3的连续子串包含'A','B','C',所以是暗黑的字符串
 你的任务就是计算出长度为n的字符串(只包含'A'、'B'和'C')，有多少个是暗黑的字符串。
 * @Author personajian
 * @Date 2017/8/4 22:29
 */
public class BlackString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int count=0;
            for(int i=0;i<n;i++){
                String str=in.next();
                if(isBlackString(str)) count++;
            }
            System.out.println(count);
        }
        in.close();
    }

    public static boolean isBlackString(String str){
        //int step=0;
        for(int i=0;i<str.length()-2;i++){
            String sub=str.substring(i,i+3);
            if(sub.contains("A")&&sub.contains("B")&&sub.contains("C")) return false;
        }
        return true;
    }
}
