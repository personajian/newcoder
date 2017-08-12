package cn.edu.seu.itcompany.neteasy;

import java.util.*;

/**
 * @Author personajian
 * @Date 2017/8/11 20:48
 */
public class UglyNumber {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            int n=in.nextInt();
            System.out.println(getUglyNumber1(n));
        }
        in.close();;
    }


    private static int getUglyNumber(int index) {
        int[] baseUgly={2,3,5};
        TreeMap<Integer,Integer> ugly=new TreeMap<>();
        ugly.put(2,null);
        ugly.put(3,null);
        ugly.put(5,null);

        while(true){
            Set<Integer> keys=new TreeSet<>(ugly.keySet());
            for(int key:keys){
                    if(ugly.size()==index-1) return ugly.lastKey();
                    int min=min(2*key,3*key,5*key);
                    if(!ugly.containsKey(min)) ugly.put(min,null);
                }
            }
    }
    /**剑指offer上的解法二
     * @Param
     * @Return
     */
    private static int getUglyNumber1(int index) {
        if(index==0) return 0;
        int[] numbers=new int[index];
        numbers[0]=1;
        int next=1;

        int ugly2=0;
        int ugly3=0;
        int ugly5=0;

        while(next<index){
            int min=min(numbers[ugly2]*2,numbers[ugly3]*3,numbers[ugly5]*5);
            numbers[next]=min;
            //需要更新ugly2，ugly3，ugly5的位置，避免重复计算
            while(numbers[ugly2]*2<=numbers[next])
                ugly2++;
            while(numbers[ugly3]*3<=numbers[next])
                ugly3++;
            while(numbers[ugly5]*5<=numbers[next])
                ugly5++;
            next++;
        }

        return numbers[next-1];
    }

    private static int min(int a, int b, int c ){
        int m=(a<b)?a:b;
        m=(m<c)?m:c;
        return m;
    }
}
