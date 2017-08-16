package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.LinkedList;

/**从上往下打印出二叉树的每个节点，同层节点从左至右打印。（单纯的打印，不分层打印。）
 * @Author personajian
 * @Date 2017/8/16 21:38
 */
public class T23_PrintTree {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        //存储在列表中打印
        ArrayList<Integer> list=new ArrayList<Integer>();

        if(root==null)
            return list;

        //利用辅助队列
        LinkedList<TreeNode> queue=new LinkedList<TreeNode>();
        //根节点首先入队！
        queue.offer(root);



        while(!queue.isEmpty()){
            //出队队头元素
            TreeNode treeNode=queue.poll();
            //读取当前节点
            list.add(treeNode.val);
            if(treeNode.left!=null)
                queue.offer(treeNode.left);
            if(treeNode.right!=null)
                queue.offer(treeNode.right);
        }
        return list;

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
