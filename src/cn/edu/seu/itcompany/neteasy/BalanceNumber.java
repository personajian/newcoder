package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/8/11 11:45
 */
public class BalanceNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            System.out.println(isBalanceNumber(n));
        }
        in.close();
    }

    private static String isBalanceNumber(int n){
        String str=String.valueOf(n);
        char[] ch=str.toCharArray();
        int start=0;
        int end=str.length()-1;


        int zeros=0;
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='0') zeros++;
        }
        //包含1个0不是平衡数
        if(zeros==1) return "NO";
        //包含2个以上0一定是平衡数
        if(zeros>1) return "YES";
        //一位整数，不是平衡数
        if(start==end) return "NO";

        int multiFront= 1;
        int multiPost=1;
        do{
            if(multiFront<=multiPost) {
                multiFront*=Integer.parseInt(String.valueOf(str.charAt(start)));
                start++;
            }else if(multiFront>multiPost){
                multiPost*=Integer.parseInt(String.valueOf(str.charAt(end)));
                end--;
            }
        }while(start<=end);

        if(multiFront==multiPost)
            return "YES";
        else
            return "NO";
    }
}
