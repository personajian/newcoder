package cn.edu.seu.itcompany.neteasy;

import java.util.LinkedList;
import java.util.Scanner;

/**[编程题]循环单词
 * 如果一个单词通过循环右移获得的单词，我们称这些单词都为一种循环单词。
 * 例如：picture 和 turepic 就是属于同一种循环单词。 现在给出n个单词，需要统计这个n个单词中有多少种循环单词。
 * https://www.nowcoder.com/questionTerminal/9d5fbe7750a34d0b91c73943f93b2d7d
 * @Author personajian
 * @Date 2017/8/11 10:45
 */
public class CycleWord {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                int n=in.nextInt();
                LinkedList<String> list=new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    list.add(in.next());
                }

                int count=0;
                while(!list.isEmpty()){
                    String str1=list.getFirst();
                    int index=1;
                    while(index<list.size()){
                        if(cycleWord(str1,list.get(index)))
                            list.remove(index);

                        else
                            index++;
                    }
                    list.removeFirst();
                    count++;
                }
                System.out.println(count);
            }
            in.close();
        }

        private static boolean cycleWord(String str1,String str2){
            if(str1.length()!=str2.length()) return false;
            else{
                str2+=str2;
                return str2.contains(str1);
            }
        }
}
