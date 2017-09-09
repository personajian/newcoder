package cn.edu.seu.swordoffer;

/**
 * 链表中环的入口
 * 算法思想：
 * 1. 判断一个链表是否有环：设置快慢指针，慢指针能与快指针相遇，就一定有环。
 * 2. 根据有环，再计算出环中的节点数
 * 3. 根据环中的结点数，再用两个先后指针计算出环的入口
 *
 * @Author personajian
 *
 * @Date 2017/9/3 20:32
 */
public class T56_EntryNodeOfLoop {

    public static void main(String[] args) {
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(2);
        ListNode n3=new ListNode(3);
        ListNode n4=new ListNode(4);
        ListNode n5=new ListNode(5);
        ListNode n6=new ListNode(6);

        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n3;

        ListNode entry= new T56_EntryNodeOfLoop().EntryNodeOfLoop(n1);
    }


    public ListNode EntryNodeOfLoop(ListNode pHead) {
        int numLoop = numLoop(pHead);
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        ListNode entryNode=null;

        if (numLoop == 0) {
            entryNode=null;
        } else {
            //p1指针先走numLoop步
            while (numLoop > 0) {
                p1 = p1.next;
                numLoop--;
            }
            //p1,p2指针再同时向前移动
            while (true) {
                if (p1 == p2){
                    entryNode=p1;
                    break;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
        }

        return entryNode;
    }

    private int numLoop(ListNode pHead) {
        ListNode p = pHead;
        ListNode slow = p;
        ListNode fast = p;
        ListNode temp = null;
        int numLoop = 0;
        boolean flag = false;

        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                temp = slow;
                break;
            }
        }
        //若有环，返回计算环中的节点数
        if (flag) {
            ListNode t = temp;
            while (true) {
                numLoop++;
                t = t.next;
                if (temp == t) {
                    break;
                }
            }
            return numLoop;

        } else//若无环，返回0
            return 0;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}