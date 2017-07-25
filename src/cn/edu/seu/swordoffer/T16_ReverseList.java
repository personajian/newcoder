package cn.edu.seu.swordoffer;

/**反转链表
 * @Author personajian
 * @Date 2017/7/25 21:23
 */

public class T16_ReverseList {

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=null;

        ListNode reverse=ReverseList(l1);
        ListNode p=reverse;
        while(p!=null){
            System.out.print(p.val);
            System.out.print(" ");
            p=p.next;
        }
    }

    public static ListNode ReverseList(ListNode head) {
        if(head==null) return null;//空结点时，直接返回null
        else if(head.next==null) return head;//只有一个结点时，直接返回单个结点
        else{//链表结点超过1个时，方法通用
            ListNode p=head.next;
            ListNode pre=head;
            head.next=null;//首结点的指针域需要特殊处理，置空。
            while(p!=null){//p是当前结点，pre是p的前驱，q是pre的后继。小心操作，以防断链
                ListNode q=p.next;//一定要保存后继结点
                p.next=pre;//修改指针
                pre=p;//更新前驱结点
                p=q;//继续...
            }
            return pre;//结束标记是p为null，于是返回p的前驱pre作为链表。
        }

    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}