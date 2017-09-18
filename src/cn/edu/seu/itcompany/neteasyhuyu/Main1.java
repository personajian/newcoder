package cn.edu.seu.itcompany.neteasyhuyu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            boolean result = isLegal(line);
            System.out.println(result);
        }
        in.close();
    }

    private static boolean isLegal(String line) {
        Map<Character, Character> cache = new HashMap<>();
        cache.put('(', ')');
        cache.put('[', ']');
        cache.put('{', '}');
        char[] strs = line.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < strs.length; i++) {
            char temp = strs[i];
            if (isLegalCharacter(temp)) {
                if (')' == temp || ']' == temp || '}' == temp) {
                    if (stack.isEmpty())
                        return false;
                    char element = stack.pop();
                    if (cache.get(element) != temp)
                        return false;
                } else if ('(' == temp || '[' == temp || '{' == temp) {
                    stack.push(temp);
                }
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }

    private static boolean isLegalCharacter(char temp) {
        return ')' == temp || ']' == temp || '}' == temp ||
                '(' == temp || '[' == temp || '{' == temp;
    }
}
