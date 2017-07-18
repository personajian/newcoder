package cn.edu.seu.itcompany.huawei;

//Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}

/**
 * @author personajian
 * 参考cn.edu.seu.leetcode.Solution2
 * [编程|300分] 七进制大数乘法
	时间限制：3秒
	空间限制：32768K
	题目描述
	将两个无符号的七进制数进行相乘，输出也是七进制，数字可能很大
	输入描述:
	两个数用单个空格分开。输入字符串只包含'0'-'6'及空格' '。输入的七进制数位长不超过11.
	
	
	输出描述:
	字符串，包含字符'0'-'6'
	
	输入例子:
	14 2
	
	输出例子:
	31
 */
public class Fxtwo {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  	int length1=getLength(l1);
  	int length2=getLength(l2);
      int maxLength=Math.max(length1, length2);
      ListNode node;
      ListNode minListNode=length1==maxLength?l2:l1;
      ListNode l0 = new ListNode(0);
      ListNode sum= new ListNode(0);
      
      //将短链表用0填充
  	node=minListNode;
  	for(int i=0;i<maxLength;i++){
  		if(node.next==null){
  			node.next=new ListNode(0);
  		}
  		node=node.next;
  	}
  	
  	//初始化进位链表，0填充
  	for(int i=1;i<maxLength;i++){
  		node=new ListNode(0); 
  		node.next=l0.next;
  		l0.next=node;
  	}
  	//初始化求和链表，0填充
  	for(int i=1;i<maxLength;i++){
  		node=new ListNode(0); 
  		node.next=sum.next;
  		sum.next=node;
  	}

  	ListNode node1=l1;
  	ListNode node2=l2;
  	ListNode node0=l0;
  	ListNode nodesum=sum;
  	int plus=0;

  	int sumary,one,ten;
  	for (int i = 0; i < maxLength; i++) {
			sumary=node1.val+node2.val+node0.val+plus;
			one=sumary%7;
			ten=sumary/7;
			plus=ten;
			nodesum.val=one;
			if(i==maxLength-1&&plus!=0){
				nodesum.next=new ListNode(plus);;
			}
			nodesum=nodesum.next;
			node1=node1.next;
			node2=node2.next;
			node0=node0.next;
		}
  	
  	
  	return sum;
  }
  
  public int getLength(ListNode l){
      	int length=0;
      	ListNode node=l;
      	while(node!=null){
      		length++;
      		node=node.next;
      	}
  		return length;
  	}
  
  public static void main(String[] args) {
		ListNode l1=new ListNode(4);
		l1.next=new ListNode(1);
		//l1.next.next=new ListNode(3);
		
		/*ListNode l2=new ListNode(5);
		l2.next=new ListNode(6);
		l2.next.next=new ListNode(9);
		
		ListNode l1=new ListNode(1);
		l1.next=new ListNode(8);*/
		
		ListNode l2=new ListNode(4);
		l2.next=new ListNode(1);
		
		ListNode node=l1;
		
		while(node!=null){
			System.out.print(node.val);
			node=node.next;
		}
		System.out.println();
		
		node=l2;
		while(node!=null){
			System.out.print(node.val);
			node=node.next;
		}
		System.out.println();
		
		ListNode l=new Fxtwo().addTwoNumbers(l1, l2);
		
		while(l!=null){
			System.out.print(l.val);
			l=l.next;
		}
	}
}
