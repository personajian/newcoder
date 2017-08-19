package cn.edu.seu.swordoffer;

/**找出中序遍历中的下一个结点。
 * 题设：二叉树的结点有左右子树的指针，并且有指向父结点的指针。
 * 对于中序遍历分三种情况：
 * 1. 若该结点有右子树，则该结点的下一个结点是该结点右子树的最左结点；
 * 2. 若该结点没有右子树，并且该结点是其父结点的左子结点，则该结点的下一个结点就是其父结点；
 * 3. 若该结点没有右子树，并且该结点是其父结点的右子结点，则该结点的下一个结点就是沿其父结点一直向上遍历到，一个是祖结点的左子结点的结点。
 *
 * @Author personajian
 * @Date 2017/8/18 20:55
 */
public class T58_GetNextNode {

    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        if(pNode==null)
            return null;

        TreeLinkNode next=null;

        //1. 若该结点有右子树，则该结点的下一个结点是该结点右子树的最左结点；
        if(pNode.right!=null){
            TreeLinkNode p=pNode.right;
            while(p.left!=null){
                p=p.left;
            }
            next=p;
        }
        //2. 若该结点没有右子树，并且该结点是其父结点的左子结点，则该结点的下一个结点就是其父结点；
        //3. 若该结点没有右子树，并且该结点是其父结点的右子结点，则该结点的下一个结点就是沿其父结点一直向上遍历到，一个是其父结点的左子结点的结点。
        else if(pNode.next!=null){
            TreeLinkNode cur=pNode;
            TreeLinkNode par=pNode.next;
            while(par!=null&&cur==par.right){
                cur=par;
                par= par.next;
            }
            next=par;
        }

        return next;
    }

    /**牛客网友解法：
     * 只分两种情况：
     * 1. //如果有右子树，则找右子树的最左节点
     * 2. //没右子树，则找第一个（当前节点是父节点左孩子）的节点
     * @Param
     * @Return
     */
    public TreeLinkNode GetNext1(TreeLinkNode node)
    {
        if(node==null) return null;
        if(node.right!=null){ //如果有右子树，则找右子树的最左节点
            node = node.right;
            while(node.left!=null) node = node.left;
            return node;
        }
        while(node.next!=null){ //没右子树，则找第一个当前节点是父节点左孩子的节点
            if(node.next.left==node) return node.next;
            node = node.next;
        }
        return null; //退到了根节点仍没找到，则返回null
    }



    private class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}