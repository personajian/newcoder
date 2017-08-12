package cn.edu.seu.itcompany.neteasy;

import java.util.LinkedList;
import java.util.Scanner;

/**https://www.nowcoder.com/questionTerminal/5f2186b48691435388ceccc1269e212a
 * @Author personajian
 * @Date 2017/8/11 22:14
 */
public class ExpressionEvaluation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str=in.next();
            System.out.println(expressionEvaluation(str));
        }
        in.close();
    }


    private static String expressionEvaluation(String str){
        char[] ch=str.toCharArray();
        LinkedList<Character> chars=new LinkedList<>();
        LinkedList<Integer> stackNumber=new LinkedList<>();
        LinkedList<Character> stackSingal=new LinkedList<>();
        int result=0;

        for(char c:ch){
            chars.add(c);
        }

        while(true){
            if(stackNumber.size()==2&&stackSingal.size()==1){
                int m=stackNumber.pop();
                int n=Integer.parseInt(String.valueOf(stackNumber.pop()));
                char operation=stackSingal.pop();
                switch (operation){
                    case '-':
                        result=n-m;
                        break;
                    case '*':
                        result=n*m;
                        break;
                    case '+':
                        result=n+m;
                        break;
                    default:
                        return "ERROR";
                }
                stackNumber.push(result);
            }

            if(stackNumber.size()==1&&stackSingal.size()==1){
                int temp=Integer.parseInt(String.valueOf(chars.poll()));
                stackNumber.push(temp);
            }

            if(stackNumber.size()==0&&stackSingal.size()==1){
                int temp1=Integer.parseInt(String.valueOf(chars.poll()));
                int temp2=Integer.parseInt(String.valueOf(chars.poll()));
                stackNumber.push(temp1);
                stackNumber.push(temp2);
            }

            if(chars.size()!=0&&stackNumber.size()==0&&stackSingal.size()==0){
                int temp1=Integer.parseInt(String.valueOf(chars.poll()));
                char signal=chars.poll();
                int temp2=Integer.parseInt(String.valueOf(chars.poll()));

                stackNumber.push(temp1);
                stackSingal.push(signal);
                stackNumber.push(temp2);
            }

            if(chars.size()!=0&&stackNumber.size()!=0&&stackSingal.size()==0){
                char signal=chars.poll();
                stackSingal.push(signal);
            }

            if(chars.size()==0&&stackNumber.size()==1&&stackSingal.size()==0)
                return String.valueOf(stackNumber.pop());
        }

    }

}
