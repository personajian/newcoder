package cn.edu.seu.swordoffer;

import java.util.LinkedList;

public class T5_PrintListReserve {

	/**从尾到头打印链表：采用辅助栈
	 * @param head
	 */
	public void PrintListReserveIteratively(ListNode head) {
		LinkedList<ListNode> stack=new LinkedList<>();
		
		ListNode p=head;
		while(p!=null) {
			stack.push(p);
			p=p.next;
		}
		
		while(!stack.isEmpty()) {
			p=stack.pop();
			System.out.println(p.value);
		}
	}
		
	public void PrintListReserveRecursively(ListNode head) {
		if(head!=null) {
			if(head.next!=null) PrintListReserveRecursively(head.next);
		}
		System.out.println(head.value);
	}
	
	private static class ListNode{
		int value;
		ListNode next;
		
		ListNode(int value,ListNode next) {
			this.value=value;
			this.next=next;
		}
	}
	
}
