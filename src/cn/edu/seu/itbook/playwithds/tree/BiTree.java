package cn.edu.seu.itbook.playwithds.tree;

import java.util.Scanner;

/**
 * @author personajian
 * 二叉树
 */
public class BiTree {
	
	int data;//数据域
	BiTree lChild;//左子树
	BiTree rChild;//右子树
	
	/**
	 * 二叉树的前序遍历：递归
	 */
	public void preOrder() {
		BiTree tree=this;
		if(tree==null) return;
		System.out.println(tree.data);
		tree.lChild.preOrder();
		tree.rChild.preOrder();
	}
	
	/**
	 * 二叉树的中序遍历：递归
	 */
	public void inOrder() {
		BiTree tree=this;
		if(tree==null) return;
		tree.lChild.inOrder();
		System.out.println(tree.data);
		tree.rChild.inOrder();
	}
	
	/**
	 * 二叉树的后序遍历：递归
	 */
	public void postOrder() {
		BiTree tree=this;
		if(tree==null) return;
		tree.lChild.postOrder();
		tree.rChild.postOrder();
		System.out.println(tree.data);
	}
	
	public BiTree() {
		data=0;
		lChild=null;
		rChild=null;
	}
	
	public BiTree(int data, BiTree lChild, BiTree rChild) {
		super();
		this.data = data;
		this.lChild = lChild;
		this.rChild = rChild;
	}

	/**
	 * 根据标准输入流来建立二叉树
	 */
	void createBiTree() {
		BiTree tree=this;
		Scanner in=new Scanner(System.in);
		while(in.hasNext()) {
			int data=in.nextInt();
			if(data==-1) tree=null;
			else {
				tree=new BiTree();
				tree.data=data;
				tree.lChild.createBiTree();//递归建立左子树
				tree.rChild.createBiTree();//递归建立右子树
			}
		}
	}
}
