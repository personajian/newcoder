package cn.edu.seu.swordoffer;

import java.util.Stack;

public class T21_MinStack {

    private Stack<Integer> stack=new Stack<>();

    private Stack<Integer> min=new Stack<>();

    public void push(int node) {
        if(stack.isEmpty()){
            stack.push(node);
            min.push(node);
        }
        else {
            if(stack.peek()>node) min.push(node);
            else min.push(min.peek());
            stack.push(node);
        }
    }
    
    public void pop() {
        stack.pop();
        min.pop();
    }
    
    public int top() {
        return  stack.peek();
    }
    
    public int min() {
        return min.peek();
    }
}