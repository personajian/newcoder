package cn.edu.seu.swordoffer;

public class T9_Fibonacci {

    public int Fibonacci(int n) {

        int[] result={0,1};
        if(n<=1) return result[n];

        int fib1=1;
        int fib2=0;
        int fibN=0;

        for(int i=2;i<=n;i++){
            fibN=fib1+fib2;

            fib2=fib1;
            fib1=fibN;
        }

        return fibN;
    }

    /**递归方式，很耗时
     * @Param
     * @Return
     */
    private int fibonacci(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }
}