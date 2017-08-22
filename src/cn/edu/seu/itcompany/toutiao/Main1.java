package cn.edu.seu.itcompany.toutiao;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args){
        Queue<zuobiao> l = new PriorityQueue<>(new Comparator<zuobiao>(){
            @Override
            public int compare(zuobiao z1, zuobiao z2){
                return (z1.y<z2.y)?1:-1;
            }

        });

        // List<zuobiao> l = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0 ; i< n;i++){
            zuobiao z = new zuobiao();

            z.x = in.nextInt();
            z.y = in.nextInt();
            l.add(z);
        }
//        Collections.sort(l,new Comparator<zuobiao>(){
//            @Override
//            public int compare(zuobiao z1, zuobiao z2){
//                return (z1.y<z2.y)?1:-1;
//            }
//
//        });

        // System.out.println(l.poll().x);

        int len =l.size();
        zuobiao temp = l.poll();
        System.out.println(temp.x+" "+temp.y);
        for (zuobiao z:l){
            if (z.x>temp.x){
                temp =z;
                System.out.println(temp.x+" "+temp.y);
            }
        }

    }
    
    static class zuobiao{
        int x;
        int y;
    }
}
