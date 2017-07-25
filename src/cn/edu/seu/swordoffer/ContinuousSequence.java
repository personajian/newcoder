package cn.edu.seu.swordoffer;

import java.util.*;

/**
 * @Author personajian
 * @Date 2017/7/25 14:27
 */
public class ContinuousSequence {
    public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists=new ArrayList<ArrayList<Integer>>();
        Map<Integer,Integer> map=new TreeMap<Integer,Integer>() ;
        for(int m=1;m<sum;m++){
            if(2*sum%m==0){
                int remain=(2*sum/m-m+1)%2;
                int x=(2*sum/m-m+1)/2;
                if(remain==0&&x>0) map.put(x,m);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ArrayList<Integer> list=new ArrayList<Integer>();
            int x=entry.getKey();
            int m=entry.getValue();
            for(int i=0;i<m;i++){
                list.add(x+i);
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lists=FindContinuousSequence(100);
        for(ArrayList<Integer> list:lists){
            for(Integer i:list){
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
