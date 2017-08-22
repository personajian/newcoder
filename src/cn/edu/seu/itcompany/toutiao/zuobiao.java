package cn.edu.seu.itcompany.toutiao;
import java.util.*;

/**
 * Created by liunian on 2017/8/22.
 */
public class zuobiao {
    int x;
    int y;
    public static void main(String[] args){
//        Queue<Integer> q = new PriorityQueue<>();
        List<zuobiao> l = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0 ; i< n;i++){
            zuobiao z = new zuobiao();

            z.x = in.nextInt();
            z.y = in.nextInt();
            l.add(z);
        }
        Collections.sort(l,new Comparator<zuobiao>(){
            @Override
            public int compare(zuobiao z1, zuobiao z2){
                return (z1.y<z2.y)?1:-1;
            }

        });



        int len =l.size();
        zuobiao temp = l.get(0);
        System.out.println(temp.x+" "+temp.y);
        for (int i =1;i<len;i++){
            if (l.get(i).x>temp.x){
                temp =l.get(i);
                System.out.println(temp.x+" "+temp.y);
            }
        }

    }
}
