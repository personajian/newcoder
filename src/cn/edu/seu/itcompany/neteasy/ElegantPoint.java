package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**优雅的点
 * 小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
 例如：半径的平方如果为25
 优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
 * @Author personajian
 * @Date 2017/8/2 22:39
 */
public class ElegantPoint {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int r2=in.nextInt();
            double rd=Math.sqrt(r2);
            int count=0;

            if(rd%1==0) count+=4;

            int r=(int)rd;

            for(int i=1;i<r;i++){
                for(int j=r;j>=i;j--){
                    if(i*i+j*j==r2){
                        if(i!=j) count+=8;
                        else count+=4;
                    }
                }
            }
            System.out.println(count);
        }
        in.close();
    }
}
