package cn.edu.seu.itbook.mydatastruct;

import cn.edu.seu.itbook.mydatastruct.MyLinkedList;

public class MyStack<T> {

	static class Node<T> {
		T data;
		Node<T> next;

		Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		Node(T data) {
			this(data, null);
		}
	}

	@SuppressWarnings("rawtypes")
	static MyLinkedList list = new MyLinkedList();

	@SuppressWarnings("unchecked")
	public T push(T item) {
		list.addFromHead(item);
		return item;
	}

	public void pop() {
		list.removeFromHead();
	}

	public boolean empty() {
		return list.isEmpty();
	}

	public int search(T t) {
		return list.indexOf(t);
	}

	public static void main(String[] args) {
		MyStack<String> stack = new MyStack<String>();
		System.out.println(stack.empty());
		stack.push("abc");
		stack.push("def");
		stack.push("egg");
		stack.pop();
		System.out.println(stack.search("def"));
	}
}
