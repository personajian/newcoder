package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**买苹果
 * 小易去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装(包装不可拆分)。
 * 可是小易现在只想购买恰好n个苹果，小易想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小易将不会购买。
 * @Author personajian
 * @Date 2017/8/5 0:40
 */
public class BuyApple {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            System.out.println(buyApple(n));
        }
        in.close();
    }

    private static int buyApple(int n){
        if((n&1)==1) return -1;

        int x=n/6;
        int y=n/8;
        int remain=0;
        int count=0;


        for(int j=y;j>=0;j--){
            remain=n-8*j;
            if(remain%6==0) return j+remain/6;
        }
        return -1;
    }
}
