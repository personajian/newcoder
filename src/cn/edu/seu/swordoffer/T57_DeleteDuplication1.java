package cn.edu.seu.swordoffer;

/**删除链表中的重复节点，推荐解法，新增一个虚拟头节点辅助操作。
 * @Author personajian
 * @Date 2017/8/15 20:52
 */
public class T57_DeleteDuplication1 {

    public static void main(String[] args) {
        ListNode pHead=new ListNode(1);
        pHead.next=new ListNode(1);
        pHead.next.next=new ListNode(1);
        pHead.next.next.next=new ListNode(1);
        pHead.next.next.next.next=new ListNode(1);
        pHead.next.next.next.next.next=new ListNode(2);
        pHead.next.next.next.next.next.next=new ListNode(4);

        ListNode p=pHead;
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }
        System.out.println();
        pHead=deleteDuplication(pHead);

        p=pHead;
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }

    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if(pHead==null) return null;
        if(pHead.next==null) return pHead;

        //在链表之前加一个虚拟头节点，作为辅助
        ListNode firstNode=new ListNode(-1);

        if(firstNode.val==pHead.val)
            firstNode.val=-2;

        firstNode.next=pHead;

        ListNode pre=firstNode;
        ListNode p=pre.next;

        while(p!=null){
            while(p.next!=null&&p.val==p.next.val){//当前节点值与下一个节点值相等的话，循环删除下一个节点
                p=p.next;
            }
            if(pre.next!=p){//表示p节点已经是重复点了，故更新pre和p以跳过当前p节点（删除）
                p=p.next;
                pre.next=p;
            }else{//表示p节点未重复，正常更新pre和p
                pre=p;
                p=p.next;
            }

        }
        //返回剔除了虚拟头节点的链表
        return firstNode.next;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

