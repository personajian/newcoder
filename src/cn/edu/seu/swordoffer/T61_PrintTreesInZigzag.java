package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**按之字形顺序打印二叉树
 * 算法思想：用两个栈分别保存奇数行、偶数行的结点。
 * @Author personajian
 * @Date 2017/7/29 17:20
 */
public class T61_PrintTreesInZigzag {

    public  void PrintTreesInZigzag(TreeNode root){
        if(root==null) return;

        List<Stack<TreeNode>> levels=new ArrayList<Stack<TreeNode>>();
        int current=0;
        int next=1;

        levels.get(current).push(root);
        while(!levels.get(current).isEmpty()||!levels.get(next).isEmpty()){
            //获得栈顶元素，后续将其子结点压栈
            TreeNode pNode = levels.get(current).pop();

            System.out.print(pNode.val+" ");

            if(current==0){//偶数行，先压入左子结点，再压入右子结点
                if(pNode.left!=null) levels.get(next).push(pNode.left);
                if(pNode.right!=null) levels.get(next).push(pNode.right);
            }else{//奇数行，先压入右子结点，再压入左子结点
                if(pNode.right!=null) levels.get(next).push(pNode.right);
                if(pNode.left!=null) levels.get(next).push(pNode.left);
            }

            //**一层的所有结点都打印完了，需要交换这两个栈并继续打印下一行
            if(levels.get(current).isEmpty()){
                System.out.println();
                current=1-current;
                next=1-next;
            }
        }

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
