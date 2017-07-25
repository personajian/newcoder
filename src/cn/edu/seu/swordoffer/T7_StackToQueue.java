package cn.edu.seu.swordoffer;

import java.util.Stack;

public class T7_StackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        T7_StackToQueue stq=new T7_StackToQueue();
        stq.push(1);
        stq.push(2);
        stq.push(3);
        stq.push(4);

        System.out.println(stq.pop());
        System.out.println(stq.pop());
        System.out.println(stq.pop());
        System.out.println(stq.pop());
    }
}