package cn.edu.seu.itcompany.meituan.main3;

import java.util.Scanner;

public class meituan {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();


        int arr[]=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=s.nextInt();
        }
        int k=s.nextInt();

        if(n<1||n>100000||k<1||k>100000)return;
        System.out.println(fun(arr,k));
    }
    public static int fun(int a[],int k){
        int max=Integer.MIN_VALUE;
        int sum;
        int count = 0;
        for(int i = 0; i < a.length; i++) {
            sum = 0;
            for(int j = i; j < a.length; j++) {
                sum += a[j];
                if(sum%k == 0&&max<j-i+1){
                    max=j-i+1;
                }
            }
        }
        return max;

    }
}
