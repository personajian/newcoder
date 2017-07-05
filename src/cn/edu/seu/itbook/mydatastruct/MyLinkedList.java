package cn.edu.seu.itbook.mydatastruct;

public class MyLinkedList<T> {

	/**
	 * class node
	 * @author egg
	 * @param <T>
	 */
	private static class Node<T> {
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

	// data
	private Node<T> head, tail;

	public MyLinkedList() {
		head = tail = null;
	}

	/**
	 * judge the list is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * add head node
	 */
	public void addHead(T item) {
		head = new Node<T>(item);
		if (tail == null)
			tail = head;
	}

	/**
	 * add the tail pointer
	 */
	public void addTail(T item) {
		if (!isEmpty()) { 
			tail.next = new Node<T>(item);
			tail = tail.next;
		} else { 
			head = tail = new Node<T>(item);
		}
	}

	/**
	 * print the list
	 */
	public void traverse() {
		if (isEmpty()) {
			System.out.println("null");
		} else {
			for (Node<T> p = head; p != null; p = p.next)
				System.out.println(p.data);
		}
	}

	/**
	 * insert node from head
	 */
	public void addFromHead(T item) {
		Node<T> newNode = new Node<T>(item);
		newNode.next = head;
		head = newNode;
	}

	/**
	 * insert node from tail
	 */
	public void addFromTail(T item) {
		Node<T> newNode = new Node<T>(item);
		Node<T> p = head;
		while (p.next != null)
			p = p.next;
		p.next = newNode;
		newNode.next = null;
	}

	/**
	 * delete node from head
	 */
	public void removeFromHead() {
		if (!isEmpty())
			head = head.next;
		else
			System.out.println("The list have been emptied!");
	}

	/**
	 * delete frem tail, lower effect
	 */
	public void removeFromTail() {
		Node<T> prev = null, curr = head;
		while (curr.next != null) {
			prev = curr;
			curr = curr.next;
			if (curr.next == null)
				prev.next = null;
		}
	}

	/**
	 * insert a new node
	 * @param appointedItem
	 * @param item
	 * @return
	 */
	public boolean insert(T appointedItem, T item) {
		Node<T> prev = head, curr = head.next, newNode;
		newNode = new Node<T>(item);
		if (!isEmpty()) {
			while ((curr != null) && (!appointedItem.equals(curr.data))) {
				prev = curr;
				curr = curr.next;
			}
			newNode.next = curr; 
			prev.next = newNode;
			return true;
		}
		return false; 
	}

	public void remove(T item) {
		Node<T> curr = head, prev = null;
		boolean found = false;
		while (curr != null && !found) {
			if (item.equals(curr.data)) {
				if (prev == null)
					removeFromHead();
				else
					prev.next = curr.next;
				found = true;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}


	public int indexOf(T item) {
		int index = 0;
		Node<T> p;
		for (p = head; p != null; p = p.next) {
			if (item.equals(p.data))
				return index;
			index++;

		}
		return -1;
	}

	/**
	 * judge the list contains one data
	 */
	public boolean contains(T item) {
		return indexOf(item) != -1;
	}
}