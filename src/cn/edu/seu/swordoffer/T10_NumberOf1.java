package cn.edu.seu.swordoffer;

public class T10_NumberOf1 {


    public int NumberOf1(int n) {
        char[] chars=Integer.toBinaryString(n).toCharArray();
        int sum=0;
        for(char ch:chars){
            if(ch=='1')
                sum++;
        }
        return sum;
    }
}