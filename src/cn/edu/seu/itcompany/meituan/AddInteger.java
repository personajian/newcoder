/*
package cn.edu.seu.itcompany.meituan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

*/
/**[编程题]整数加法
 * https://www.nowcoder.com/questionTerminal/850fde3d987f4b678171abd88cf05710?commentTags=Java
 * @Author personajian
 * @Date 2017/8/31 17:11
 *//*

public class AddInteger {

    private static Character[] charNum={'0','1','2','3','4','5','6','7','8','9'};
    private static Integer[] intNum={0,1,2,3,4,5,6,7,8,9};
    private static List<Character> charNumList= Arrays.asList(charNum);
    private static List<Integer> intNumList= Arrays.asList(intNum);



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String str1=in.next();
            String str2=in.next();
            System.out.println(addInteger(str1,str2));
        }
    }

    private static String addInteger(String str1,String str2){

        StringBuilder result=new StringBuilder();

        char[] chs1=str1.toCharArray();
        char[] chs2=str2.toCharArray();

        if(isError(chs1))
            return "error";
        if(isError(chs2))
            return "error";


        if(chs1[0]=='-'&&chs2[0]=='-'){
            String temp=add(str1.substring(1,str1.length()),str2.substring(1,str2.length()));
            result.append("-").append(temp);
        }else if(chs1[0]!='-'&&chs2[0]!='-'){
            String temp=add(str1,str2);
            result.append(temp);
        }else if(chs1[0]=='-'||chs2[0]=='-'){

        }

    }

    private static boolean isError(char[] chs){
        boolean flag=false;

        if(chs[0]=='-'){
            for(int i=1;i<chs.length;i++){
                if(!charNumList.contains(chs[i]))
                    flag=true;
            }
        }else{
            for(char c:chs){
                if(!charNumList.contains(c))
                    flag=true;
            }
        }

        return flag;
    }
}
*/
