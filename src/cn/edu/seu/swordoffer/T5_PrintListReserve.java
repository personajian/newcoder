package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.LinkedList;
/**从尾到头打印链表
 * @Author personajian
 * @Date 2017/7/25 22:58
 */
public class T5_PrintListReserve {

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
	/*从尾到头打印链表：递归方式
	*
	* */
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
