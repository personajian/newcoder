package cn.edu.seu.swordoffer;

/**合并两个排序的链表
 * @Author personajian
 * @Date 2017/7/25 22:11
 */

public class T17_Merge2List {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null) return list2;
        else if(list2==null) return list1;

        ListNode merge=null;
        if(list1.val<list2.val){
            merge=list1;
            merge.next=Merge(list1.next,list2);
        }else{
            merge=list2;
            merge.next=Merge(list1,list2.next);
        }
        return merge;
    }



    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
