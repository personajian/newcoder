package cn.edu.seu.itcompany.neteasy;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**https://www.nowcoder.com/questionTerminal/0b821b5d214445388eade1d304b976b2
 * @Author personajian
 * @Date 2017/8/11 10:31
 */
public class RemeberWord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n=in.nextInt();
            int m=in.nextInt();
            HashMap<String,Integer> mapN=new HashMap<>();
            HashMap<String,Integer> mapM=new HashMap<>();
            for(int i=0;i<n;i++){
                String str=in.next();
                mapN.put(str,str.length());
            }
            for(int i=0;i<m;i++){
                String str=in.next();
                mapM.put(str,str.length());
            }

            System.out.println(score(mapN,mapM));
        }
        in.close();
    }

    private static long score(HashMap<String,Integer> mapN,HashMap<String,Integer> mapM){
        long score=0;
        Set<String> key=mapN.keySet();
        for(String str:key){
            if(mapM.containsKey(str)) score+=str.length()*str.length();
        }
        return score;
    }
}
