package cn.edu.seu.swordoffer;

/**二叉树中和为某一值的路径
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * 算法思想：
 * @Author personajian
 * @Date 2017/8/16 21:51
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class FindPath {

    public static void main(String[] args) {
        TreeNode n0=new TreeNode(10);
        TreeNode n1=new TreeNode(5);
        TreeNode n2=new TreeNode(12);
        TreeNode n3=new TreeNode(4);
        TreeNode n4=new TreeNode(7);

        n0.left=n1;
        n0.right=n2;
        n1.left=n3;
        n1.right=n4;

        ArrayList<ArrayList<Integer>> paths=new ArrayList<ArrayList<Integer>>();

        paths=findPath(n0,22);

    }

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {

        ArrayList<ArrayList<Integer>> paths=new ArrayList<ArrayList<Integer>>();

        if(root==null)
            return paths;

        LinkedList<TreeNode> stack=new LinkedList<TreeNode>();

        int sum=0;

        findPathRecursion(root,target,sum,paths,stack);

        return paths;
    }

    private static void findPathRecursion
            (TreeNode root, int target, int sum,ArrayList<ArrayList<Integer>> paths ,LinkedList<TreeNode> stack){

        //辅助栈中存储着节点路径：将当前节点压入辅助栈中，并sum+
        stack.push(root);
        sum+=root.val;

        boolean isLeaf=root.left==null&&root.right==null;

        if(sum==target&&isLeaf){
            ArrayList<Integer> path=new ArrayList<Integer>();
            Iterator<TreeNode> it=stack.descendingIterator();
            while(it.hasNext()){
                path.add(it.next().val);
            }
            paths.add(path);
        }

        if(root.left!=null)
            findPathRecursion(root.left,target,sum,paths,stack);
        if(root.right!=null)
            findPathRecursion(root.right,target,sum,paths,stack);

        //回溯，删除栈顶节点，并sum-，很重要！
        sum-=root.val;
        stack.pop();
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