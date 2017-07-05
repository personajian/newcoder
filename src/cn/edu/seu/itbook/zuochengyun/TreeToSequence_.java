package cn.edu.seu.itbook.zuochengyun;

import java.util.*;

import org.junit.runner.Result;

/**
 * @author personajian
 * 二叉树先、中、后序遍历的非递归实现
 */

public class TreeToSequence_ {
    /**
     * 先序遍历：根左右
     */
    private static void firstRoot(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);//根节点入栈
        while (!stack.isEmpty()) {
            root = stack.pop();//1.栈顶弹出元素
            list.add(root.val);//2.访问（打印）节点
            if (root.right != null) {//3.右节点入栈
                stack.push(root.right);
            }
            if (root.left != null){
                stack.push(root.left);//4.左节点入栈
            }

        }
    }

    /**
     * 中序遍历：左根右
     */
    private static void middleRoot(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || root!=null) {
            if (root != null) {
                stack.push(root);//1.根节点压栈
                root = root.left;//2.左节点压栈
            }else {
                root = stack.pop();//3.弹出栈顶元素（根节点+左节点）
                list.add(root.val);//4.访问
                root = root.right;//5.更新为右节点
            }
        }
    }

    /**
     * 后序遍历（两个栈实现）
     */
    private static void lastRoot(List<Integer> list, TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);
            if (root.left != null) {
                stack1.push(root.left);
            }
            if (root.right != null) {
                stack1.push(root.right);
            }
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
    }
    
    public static int[][] convert(TreeNode root) {
        // write code here
        List<Integer> listFirst = new LinkedList<Integer>();
        List<Integer> listSecond = new LinkedList<Integer>();
        List<Integer> listEnd = new LinkedList<Integer>();
        firstRoot(listFirst, root);
        middleRoot(listSecond, root);
        lastRoot(listEnd, root);
        int[][] result = new int[3][listFirst.size()];
        for (int j = 0;j < listFirst.size();j++) {
            result[0][j] = listFirst.get(j);
            result[1][j] = listSecond.get(j);
            result[2][j] = listEnd.get(j);
        }
        return result;
    }

    public static void main(String[] args) {
		TreeNode root=new TreeNode(1);
		TreeNode node2=root.left=new TreeNode(2);
		TreeNode node3=root.right=new TreeNode(3);
		TreeNode node4=node2.left=new TreeNode(4);
		TreeNode node5=node2.right=new TreeNode(5);
		TreeNode node6=node3.left=new TreeNode(6);
		TreeNode node7=node3.right=new TreeNode(7);	
		
		int[][] result=convert(root);
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<result[i].length;j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
		
	}
}