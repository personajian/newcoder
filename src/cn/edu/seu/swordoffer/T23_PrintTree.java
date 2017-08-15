package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.LinkedList;

/**按层打印二叉树，每一层输出一行：左程云解法，增加两个辅助树节点。
 * @Author personajian
 * @Date 2017/8/15 21:57
 */
public class T23_PrintTree {


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

        for(ArrayList<Integer> arrayList:Print(t1)){
            for(int i:arrayList)
                System.out.print(i+" ");
            System.out.println();
        }
    }

    private static ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {

        ArrayList<ArrayList<Integer> > trace=new ArrayList<>();
        if(pRoot==null) return trace;

        //int level=0;
        //设置两个辅助树节点currentLast和nextLast
        TreeNode currentLast=pRoot;//currentLast表示当前正在打印行的最右节点
        TreeNode nextLast=null;//nextLast表示当前打印行的 下一行的最右节点
        //根节点先入队
        LinkedList<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(pRoot);

        ArrayList<Integer> layerList = new ArrayList<Integer>();

        while(!queue.isEmpty()){
            TreeNode treeNode=queue.poll();
            layerList.add(treeNode.val);
            //将左右树节点依次入队，并更新nextLast节点
            if(treeNode.left!=null){
                queue.add(treeNode.left);
                nextLast=treeNode.left;
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
                nextLast=treeNode.right;
            }
            //若当前节点等于当前行的最右节点，就表示需要换行了，并且更新当前打印行的最右节点为下一行的最右节点
            if(treeNode.equals(currentLast)){
                //level++;
                currentLast=nextLast;
                trace.add(layerList);
                layerList = new ArrayList<Integer>();
            }

        }
        return trace;
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