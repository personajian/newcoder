package cn.edu.seu.itcompany.neteasy;

import java.util.*;

/**[编程题] 消除重复元素
 小易有一个长度为n序列，小易想移除掉里面的重复元素，但是小易想是对于每种元素保留最后出现的那个。小易遇到了困难,希望你来帮助他。

 * @Author personajian
 * @Date 2017/8/7 21:29
 */
public class DeleteRepeatElement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] element = new int[n];
            for (int i = 0; i < n; i++) {
                element[i] = in.nextInt();
            }
            deleteRepeatElement(n,element);
        }
        in.close();
    }

    private static void deleteRepeatElement(int n,int[] element){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < element.length; i++) {
            map.put(element[i], i);
        }

        List<Integer> list=new ArrayList<>();
        for(int i:map.values()){
            list.add(i);
        }
        Collections.sort(list);
        for(int i=0;i<list.size()-1;i++)
            System.out.print(element[list.get(i)]+" ");
        System.out.println(element[list.get(list.size()-1)]);
    }
}
