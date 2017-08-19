package cn.edu.seu.swordoffer;

import java.util.ArrayList;

/**对称二叉树：原二叉树与镜像二叉树相等
 * 中序序列对称？只能AC90%，遇到树结点值都一样的二叉树，没办法判断
 * 剑指offer解法：原二叉树的前序遍历，肯定跟对称二叉树的前序遍历一模一样；全是相同值的二叉树，利用插入null标识
 * 非递归实现。
 * @Author personajian
 * @Date 2017/8/18 19:42
 */
public class T59_SymmetricalTree2 {

    public boolean isSymmetrical(TreeNode pRoot) {

        ArrayList<TreeNode> preorder=new ArrayList<>();
        ArrayList<TreeNode> preordersym=new ArrayList<>();

        preOrder(pRoot,preorder);
        preOrderSym(pRoot,preordersym);

        int length = preorder.size();


        int i=0,j=length;

        while(i<j){
            if(preorder.get(i).val!=preordersym.get(i).val)
                return false;
            i++;
        }

        return true;
    }

    private void preOrder(TreeNode pRoot , ArrayList<TreeNode> preorder){
        if(pRoot==null){
            preorder.add(new TreeNode(-1));
            return ;
        }
        preorder.add(pRoot);
        preOrder(pRoot.left,preorder);
        preOrder(pRoot.right,preorder);
    }

    private void preOrderSym(TreeNode pRoot , ArrayList<TreeNode> preordersym){
        if(pRoot==null){
            preordersym.add(new TreeNode(-1));
            return ;
        }
        preordersym.add(pRoot);
        preOrderSym(pRoot.right,preordersym);
        preOrderSym(pRoot.left,preordersym);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
