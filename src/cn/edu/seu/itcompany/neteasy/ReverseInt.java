package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**数字翻转
 * 对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
 如果 X = 123，则rev(X) = 321;
 如果 X = 100，则rev(X) = 1.
 现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？
 * @Author personajian
 * @Date 2017/8/4 22:52
 */
public class ReverseInt {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int x=in.nextInt();
            int y=in.nextInt();
            System.out.println(reverse(reverse(x)+reverse(y)));
        }
        in.close();
    }

    private static int reverse(int n){
        StringBuilder rev=new StringBuilder(String.valueOf(n)).reverse();
        String str=rev.toString();

        int index=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!='0'){
                index=i;
                break;
            }
        }
        String trimZero=str.substring(index,str.length());

        return Integer.parseInt(trimZero);
    }
}
