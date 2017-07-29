package cn.edu.seu.swordoffer;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Author personajian
 * @Date 2017/7/29 14:52
 */
public class T50_FirstParentNode {

    public ListNode FindFirstCommonNode(ListNode root, ListNode pNode1, ListNode pNode2) {
        
        if(root==null||pNode1==null||pNode2==null) return null;
        
        Stack<ListNode> path1=null;
        getNodePath(root,pNode1,path1);

        Stack<ListNode> path2=null;
        getNodePath(root,pNode2,path2);
        
        return getLastCommonNode(path1,path2);
    }
    
    /**递归求给定结点到根结点的路径
     * @Param 
     * @Return 
     */
    private boolean getNodePath(ListNode root,ListNode pNode,Stack<ListNode> path){
        if(root.equals(pNode)) return true;
        
        path.push(root);
        
        boolean found = false;
        
        while(!found&&root!=null){
            found=getNodePath(root.next,pNode,path);
        }
        
        if(!found) path.pop();
        
        return found;
        
    }

    /**求两条路径的最后公共结点
     * @Param 
     * @Return 
     */
    
    private ListNode getLastCommonNode(Stack<ListNode> path1,Stack<ListNode> path2){

        Iterator<ListNode> it1=path1.listIterator();
        Iterator<ListNode> it2=path2.listIterator();

        ListNode pLast=null;

        while(it1.hasNext()&&it2.hasNext()){
            ListNode t1=it1.next();
            ListNode t2=it2.next();
            if(t1.equals(t2))
                pLast=t1;
        }

        return pLast;
    }
    

    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
