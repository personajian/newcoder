package cn.edu.seu.itbook.algorithm4.customize;

import java.util.LinkedList;

public class MyStack<E> {
	private LinkedList<E> s;

	public MyStack() {
		s = new LinkedList<E>();
	}

	/**
	 * 压栈
	 * 
	 * @param e
	 */
	public void push(E e) {
		s.push(e);
	}

	/**
	 * 读取栈顶
	 * 
	 * @return
	 */
	public E peek() {
		return s.peek();
	}

	/**
	 * 弹出栈顶
	 * 
	 * @return
	 */
	public E pop() {
		return s.pop();
	}

	public boolean isEmpty() {
		return s.isEmpty();
	}

	public int size() {
		return s.size();
	}
}
