package cn.edu.seu.newcoder.itcompany.huawei;

/**
 * @author personajian
 * [编程|100分] 水仙花数
	时间限制：3秒
	空间限制：32768K
	题目描述
	题目描述： 水仙花数是指一个n 位数( n≥3 )，它的每个位上的数字的n 次幂之和等于它本身。（例如：1^3 + 5^3 + 3^3 = 153） 要求：输出1000范围以内所有水仙花数及水仙花数之和
	
	输入描述:
	无
	
	
	输出描述:
	1000以内所有水仙花数及总和
	
	输入例子:
	无
	
	输出例子:
	第1个水仙花数: xxx
	第2个水仙花数: xxx
	第3个水仙花数: xxx
	... 
	水仙花数总和为: xxx

 */
public class SpecialOffer1 {
    public static void main(String[] args) {
        int a=0;
        int b=0;
        int c=0;
        int sum=0;
        int count=1;
        for(int i=100;i<999;i++)
        {
            a=i/100;
            b=i/10%10;
            c=i%10;
            if(i==(a*a*a+b*b*b+c*c*c)){
                System.out.println("第"+count+"个水仙花数:"+i);
                count++;
                sum+=i;

            }


        }
        System.out.println("水仙花数总和为:"+sum);
    }
}


