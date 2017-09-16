package cn.edu.seu.itcompany.huyu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/9/16 19:37
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String[] arr =str.split(" ");

            Arrays.sort(arr, new Comparator<String>() {
                @Override
                /*public int compare(String o1, String o2) {
                    String c1=new StringBuilder().append(o1).append(o2).toString();
                    String c2=new StringBuilder().append(o2).append(o1).toString();

                    if(c1.compareTo(c2)==1)
                        return -1;
                    if(c1.compareTo(c2)==-1)
                        return 1;
                    return 0;
                }*/
                public int compare(String o1, String o2) {
                    String a = o1 + o2;
                    String b = o2 + o1;

                    if (Double.parseDouble(a) > Double.parseDouble(b))
                        return -1;
                    if (Double.parseDouble(a) < Double.parseDouble(b))
                        return 1;
                    return 0;
                }
            });

            StringBuilder sb=new StringBuilder();

            for(String s:arr)
                sb.append(s);

            System.out.println(sb.toString());

            /*for(int i=arr.length-1;i>=0;i--){
                System.out.println(arr[i]+" ");
            }*/

        }
        in.close();
    }

    /*private static class StrComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {

            *//*StringBuilder sb1=new StringBuilder().append(str1).append(str2);
            StringBuilder sb2=new StringBuilder().append(str2).append(str1);*//*

            String c1=new StringBuilder().append(o1).append(o2).toString();
            String c2=new StringBuilder().append(o2).append(o1).toString();

            if(c1.compareTo(c2)==1)
                return -1;
            if(c1.compareTo(c2)==-1)
                return 1;
            return 0;
        }
    }*/


}
