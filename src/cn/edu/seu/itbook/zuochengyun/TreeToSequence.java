package cn.edu.seu.itbook.zuochengyun;

import java.util.*;

/**
 * @author personajian
 * 二叉树先、中、后序遍历的递归实现
 */
public class TreeToSequence {
    private static void firstRoot(List<Integer> list, TreeNode root) {
        // 先序遍历
        if (root == null) {
            return;
        }
        list.add(root.val);
        firstRoot(list, root.left);
        firstRoot(list, root.right);
    }
 
    private static void middleRoot(List<Integer> list, TreeNode root) {
        // 中序遍历
        if (root == null) {
            return;
        }
        middleRoot(list, root.left);
        list.add(root.val);
        middleRoot(list, root.right);
    }
 
    private static void lastRoot(List<Integer> list, TreeNode root) {
        // 后序遍历
        if (root == null) {
            return;
        }
        lastRoot(list, root.left);
        lastRoot(list, root.right);
        list.add(root.val);
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