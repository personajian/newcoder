package cn.edu.seu.itbook.algorithm4.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author personajian
 * 二叉树先、中、后序遍历的非递归实现
 */

public class TreeToSequence_ {

    /**
     * 层次遍历：使用队列
     */
    private static void levelOrder(List<Integer> list, TreeNode root) {
        //Stack<TreeNode> stack = new Stack<TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);//根节点入队
        while (!queue.isEmpty()) {
            root = queue.remove();//1.队头移出元素
            list.add(root.val);//2.访问（打印）节点
            if (root.right != null) {//3.左节点入队
                queue.offer(root.left);
            }
            if (root.left != null) {
                queue.offer(root.right);//4.右节点入队
            }

        }
    }

    /**
     * 先序遍历：根左右
     */
    private static void preOrder(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);//根节点入栈
        while (!stack.isEmpty()) {
            root = stack.pop();//1.栈顶弹出元素
            list.add(root.val);//2.访问（打印）节点
            if (root.right != null) {//3.右节点入栈
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);//4.左节点入栈
            }

        }
    }

    /**
     * 中序遍历：左根右
     */
    private static void inOrder(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);//1.根节点压栈
                root = root.left;//2.左节点压栈
            } else {
                root = stack.pop();//3.弹出栈顶元素（根节点+左节点）
                list.add(root.val);//4.访问
                root = root.right;//5.更新为右节点
            }
        }
    }

    /**
     * 后序遍历（两个栈实现）
     * 栈1：压栈记录
     * 栈2：后序序列
     */
    private static void postOrder(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.add(root);//1.根结点入栈1
        while (!stack1.isEmpty()) {
            root = stack1.pop();//2.栈1弹出栈顶
            stack2.push(root);//3.栈2压栈
            if (root.left != null) {
                stack1.push(root.left);//4.将左结点压栈1
            }
            if (root.right != null) {
                stack1.push(root.right);//5.将右结点压栈1
            }
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);//6.栈2中记录着后序序列
        }
    }

    public static int[][] convert(TreeNode root) {
        // write code here
        /*List<Integer> listLevel = new LinkedList<Integer>();
        List<Integer> listFirst = new LinkedList<Integer>();
        List<Integer> listSecond = new LinkedList<Integer>();
        List<Integer> listEnd = new LinkedList<Integer>();*/
        List<LinkedList<Integer>> lists = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            lists.add(new LinkedList<>());
        }
        levelOrder(lists.get(0), root);
        preOrder(lists.get(1), root);
        inOrder(lists.get(2), root);
        postOrder(lists.get(3), root);
        int[][] result = new int[4][lists.get(0).size()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < lists.get(0).size(); j++) {
                result[i][j] = lists.get(i).get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = root.left = new TreeNode(2);
        TreeNode node3 = root.right = new TreeNode(3);
        TreeNode node4 = node2.left = new TreeNode(4);
        TreeNode node5 = node2.right = new TreeNode(5);
        TreeNode node6 = node3.left = new TreeNode(6);
        TreeNode node7 = node3.right = new TreeNode(7);

        int[][] result = convert(root);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }

    }
}