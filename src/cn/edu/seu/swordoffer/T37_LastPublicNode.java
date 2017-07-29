package cn.edu.seu.swordoffer;

/**两个链表的第一个公共节点
 * @Author personajian
 * @Date 2017/7/29 14:26
 */


public class T37_LastPublicNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        //求两个链表的长度
        ListNode p1=pHead1,p2=pHead2;
        int l1=0,l2=0;
        while(p1!=null){
            l1++;
            p1=p1.next;
        }
        while(p2!=null){
            l2++;
            p2=p2.next;
        }
        //求完链表后，需要将节点复位，不然为空指针异常
        p1=pHead1;
        p2=pHead2;

        if(l1==0||l2==0) return null;
        else{
            if(l1<l2){
                p1=pHead2;
                p2=pHead1;
                l1=l2;
                l2=l1;
            }

            int differ=l1-l2;

            //长链表的指针先移动到指定位置
            for(int i=0;i<differ;i++){
                p1=p1.next;
            }

            while(p1!=null&&p2!=null){
                if(p1.equals(p2)){
                    return p1;
                }
                else{
                    p1=p1.next;
                    p2=p2.next;
                }
            }
            return null;
        }

    }

    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
