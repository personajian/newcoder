package cn.edu.seu.swordoffer;

/**删除有序链表中的重复节点，此解法只能删除头节点以后的重复节点，解法有点复杂，推荐cn.edu.seu.swordoffer.T57_DeleteDuplication1
 * @Author personajian
 * @Date 2017/8/15 20:55
 */
public class T57_DeleteDuplication {

    public static void main(String[] args) {
        ListNode pHead=new ListNode(1);
        pHead.next=new ListNode(1);
        pHead.next.next=new ListNode(1);
        pHead.next.next.next=new ListNode(1);
        pHead.next.next.next.next=new ListNode(1);
        pHead.next.next.next.next.next=new ListNode(4);
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

        ListNode pre=pHead;
        ListNode p=pHead.next;

        int flag=0;//标识符：0表示当前结点未重复过，1表示当前结点已重复过

        int pFlag=0;
        if(pHead.val==pHead.next.val) pFlag=1;

        while(p!=null){
            if(p.next!=null&&p.val==p.next.val){//若当前结点值=下一个结点值：标识当前结点已重复，将下一个结点删除
                flag=1;
                p.next=p.next.next;
                //pre=p;
                //p=p.next;
            }else{//若当前结点值！=下一个结点值：先判断当前结点已重复否？是，就删除当前结点，更新指针；否，就只更新指针。
                if(flag==1){
                    flag=0;
                    p=p.next;
                    pre.next=p;
                }else{
                    pre=p;
                    p=p.next;
                }
            }
        }

        //处理首结点是重复的情况
        if(pHead.next==null){
            if(pFlag==1)
                pHead=null;
        }else{
            if(pHead.val==pHead.next.val)
                pHead=pHead.next.next;
            else{
                if(pFlag==1) pHead=pHead.next;
            }
        }


        return pHead;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

