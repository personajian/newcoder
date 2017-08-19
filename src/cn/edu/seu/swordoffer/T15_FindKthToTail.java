package cn.edu.seu.swordoffer;


public class T15_FindKthToTail {
    public ListNode findKthToTail(ListNode head, int k) {

        if(head==null)
            return null;

        ListNode p=head;

        ListNode q=head;

        for(;k>0;){
            if(p.next!=null){
                p=p.next;
                k--;
            }else {
                if(k==1)
                    return head;
                else
                    return null;
            }
        }

        while(p!=null){
            p=p.next;
            q=q.next;
        }

        return q;

    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}