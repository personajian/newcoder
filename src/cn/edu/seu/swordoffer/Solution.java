package cn.edu.seu.swordoffer;


import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {

        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {4, 5, 3, 2, 1};
        System.out.println(new Solution().IsPopOrder(pushA, popA));

    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        //出栈序列
        LinkedList<Integer> popQ = new LinkedList<>();
        //入栈序列
        LinkedList<Integer> pushQ = new LinkedList<>();

        //辅助栈
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i : popA)
            popQ.offer(i);


        for (int i : pushA)
            pushQ.offer(i);

        stack.push(pushQ.poll());

        while (!stack.isEmpty()) {

            if (stack.peek() == popQ.peek()) {//栈顶和出栈序队列一样，就出栈、出队
                stack.pop();
                popQ.poll();
            } else if (!pushQ.isEmpty())//栈顶和出栈序列不一样，就继续将入栈序列进栈
                stack.push(pushQ.poll());

            //跳出循环：当栈顶元素与出队元素不等，并且入栈序列已经为空！！
            if (stack.peek() != popQ.peek() && pushQ.isEmpty())
                break;


        }

        if (popQ.isEmpty() && pushQ.isEmpty())
            return true;
        else
            return false;

    }
}