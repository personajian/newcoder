package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**按之字形顺序打印二叉树（牛客网输出版本）
 * 算法思想：增加两个辅助数start和end。
 * @Author personajian
 * @Date 2017/7/29 17:20
 */
public class T61_PrintTreesInZigzag1 {

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(8);
        TreeNode t2=new TreeNode(6);
        TreeNode t3=new TreeNode(10);
        TreeNode t4=new TreeNode(5);
        TreeNode t5=new TreeNode(7);
        TreeNode t6=new TreeNode(9);
        TreeNode t7=new TreeNode(11);

        t1.left=t2;
        t1.right=t3;

        t2.left=t4;
        t2.right=t5;

        t3.left=t6;
        t3.right=t7;

        ArrayList<ArrayList<Integer> > lists= PrintTreesInZigzag(t1);
        System.out.println();
    }

    public  static ArrayList<ArrayList<Integer> > PrintTreesInZigzag(TreeNode pRoot){
        ArrayList<ArrayList<Integer> > lists=new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> list=new ArrayList<Integer>();

        if(pRoot==null) return lists;

        LinkedList<TreeNode> queue=new LinkedList<TreeNode>();

        queue.offer(pRoot);

        int start=0,end=1;
        boolean zig=true;

        if(pRoot==null)
            return lists;

        /*List<Stack<TreeNode>> levels=new ArrayList<Stack<TreeNode>>();
        int current=0;
        int next=1;

        levels.get(current).push(pRoot);*/
        while(!queue.isEmpty()){
            TreeNode treeNode=queue.poll();
            list.add(treeNode.val);
            start++;
            //将左右树节点依次入队
            if(treeNode.left!=null){
                queue.add(treeNode.left);
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
            }
            //若当start==end表示已经到了当前层的尾部了
            if(start==end&&zig){
                end=queue.size();//更新end为队列大小
                start=0;//更新start
                lists.add(list);
                list = new ArrayList<Integer>();
                zig=false;
            }
            if(start==end&&!zig){
                end=queue.size();//更新end为队列大小
                start=0;//更新start
                Collections.reverse(list);
                lists.add(list);
                list = new ArrayList<Integer>();
                zig=true;
            }

        }

        return lists;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
