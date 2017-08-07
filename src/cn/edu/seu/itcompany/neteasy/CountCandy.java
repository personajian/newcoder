package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**计算糖果
 * A,B,C三个人是好朋友,每个人手里都有一些糖果,我们不知道他们每个人手上具体有多少个糖果,但是我们知道以下的信息：
 A - B, B - C, A + B, B + C. 这四个数值.每个字母代表每个人所拥有的糖果数.
 现在需要通过这四个数值计算出每个人手里有多少个糖果,即A,B,C。这里保证最多只有一组整数A,B,C满足所有题设条件。
 * @Author personajian
 * @Date 2017/8/5 1:10
 */
public class CountCandy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int s1=in.nextInt();
            int s2=in.nextInt();
            int s3=in.nextInt();
            int s4=in.nextInt();

            int A=0,B=0,C=0;

            if((s4-s2)%2==1){
                System.out.println("No");
            }else{
                C=(s4-s2)/2;
                B=s2+C;
                A=s3-B;
                if((A-B==s1)&&A>=0&&B>=0&&C>=0)
                    System.out.println(A+" "+B+" "+C);
                else
                    System.out.println("No");
            }

        }
        in.close();
    }
}
