package cn.edu.seu.itcompany.neteasy;

import java.util.*;

/**
 * @Author personajian
 * @Date 2017/8/12 11:39
 */
public class FirstNotRepeatingChar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str=in.next();
            System.out.println(firstNotRepeatingChar(str));
        }
        in.close();

    }

    private static int firstNotRepeatingChar(String str){
        char[] chars=str.toCharArray();
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<chars.length;i++){
            if(map.get(chars[i])==null)
                map.put(chars[i],i);
            else{
                map.put(chars[i],-2);
            }
        }
        ArrayList<Integer> list=new ArrayList<>();
        for(Character ch:map.keySet()){
            if(map.get(ch)!=-2){
                list.add(map.get(ch));
            }
        }
        Collections.sort(list);
        return list.get(0);
    }
}
