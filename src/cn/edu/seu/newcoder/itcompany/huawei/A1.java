package cn.edu.seu.newcoder.itcompany.huawei;

public class A1 {
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


