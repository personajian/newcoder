package cn.edu.seu.swordoffer;

import java.util.LinkedList;

public class T14_ReOrderArray {
    public void reOrderArray(int [] array) {
        LinkedList<Integer> q0=new LinkedList();
        LinkedList<Integer> q1=new LinkedList<>();

        for(int i:array){
            if(i%2==0){
                q0.offer(i);
            }else
                q1.offer(i);
        }

        int len0=q0.size();
        int len1=q1.size();

        for(int i=0;i<len1;i++){
            array[i]=q1.poll();
        }
        for(int i=len1;i<array.length;i++){
            array[i]=q0.pop();
        }
    }
}