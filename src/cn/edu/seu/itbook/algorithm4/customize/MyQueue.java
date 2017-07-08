package cn.edu.seu.itbook.algorithm4.customize;

import java.util.LinkedList;

/**
 * @author personajian
 *
 * @param <E>
 */
public class MyQueue<E> {

	private LinkedList<E> q;

	public MyQueue() {
		q = new LinkedList<E>();
	}

	/**插入队尾
	 * @param e
	 */
	public void offer(E e) {
		q.offer(e);
	}

	/**读取队头
	 * @return
	 */
	public E peek() {
		return q.peek();
	}

	/**取出队头
	 * @return
	 */
	public E poll() {
		return q.poll();
	}

	public boolean isEmpty() {
		return q.isEmpty();
	}
	
	public int size() {
		return q.size();
	}
}
