package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.HashSet;

/**https://leetcode.com/problems/generate-parentheses/description/
 * @Author personajian
 * @Date 2017/9/17 21:41
 */
public class Bracket {
    public static void main(String[] args) {
        int pairs = 3;

        /*
        HashSet<String> brackets = new HashSet<>();
        bracket(3, brackets);*/


        ArrayList<String> brackets=new ArrayList<>();
        bracket0(3, brackets);

        for (String str : brackets)
            System.out.println(str);

    }


    /**方法一：递归生成，每次依据条件只生成一个（或者）。
     * @Param
     * @Return
     */
    private static void bracket0(int pairs, ArrayList<String> brackets) {

        int left = 0, right = 0;
        String str = "";

        brackets0Core(pairs, left, right, str, brackets);

    }

    private static void brackets0Core(int pairs, int left, int right, String str, ArrayList<String> brackets) {
        if (str.length() == 2*pairs) {
            brackets.add(str);
            return;
        }
        //左括号个数<对数时，增加左括号
        if (left < pairs) {
            brackets0Core(pairs, left + 1, right, str + "(", brackets);
        }
        //右括号个数<左括号时，增加右括号
        if (right < left) {
            brackets0Core(pairs, left, right + 1, str + ")", brackets);
        }
    }

    /**方法二：递归生成，每次生成一对（），会有重复，利用HashSet来去重。
     * @Param
     * @Return
     */
    private static HashSet<String> bracket(int pairs, HashSet<String> brackets) {

        String bracket = "";

        bracketsCore(brackets, bracket, pairs, 0);

        return brackets;
    }

    private static void bracketsCore(HashSet<String> brackets, String bracket, int pairs, int len) {

//        int length=bracket.length();

        if (bracket.length() == pairs * 2) {
            brackets.add(bracket);
            bracket = "";
            return;
        }

        bracketsCore(brackets, bracket + "()", pairs, len + 1);
        bracketsCore(brackets, "(" + bracket + ")", pairs, len + 1);
        bracketsCore(brackets, "()" + bracket, pairs, len + 1);
    }
}
