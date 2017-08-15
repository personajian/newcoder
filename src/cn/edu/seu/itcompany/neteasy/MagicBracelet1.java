package cn.edu.seu.itcompany.neteasy;

import java.util.Scanner;

/**[编程题] 魔力手环
 * 小易拥有一个拥有魔力的手环上面有n个数字(构成一个环),当这个魔力手环每次使用魔力的时候就会发生一种奇特的变化：
 * 每个数字会变成自己跟后面一个数字的和(最后一个数字的后面一个数字是第一个),一旦某个位置的数字大于等于100就马上对100取模(比如某个位置变为103,就会自动变为3).
 * 现在给出这个魔力手环的构成，请你计算出使用k次魔力之后魔力手环的状态。
 * @Author personajian
 * @Date 2017/8/7 21:59
 */
public class MagicBracelet1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int k=in.nextInt();
            int[] list=new int[n];
            for(int i=0;i<n;i++){
                list[i]=in.nextInt();
            }


            magicBracelet(n,k,list);
        }
        in.close();
    }

    private static void magicBracelet(int n,int k,int[] list){

    }

}
