package cn.edu.seu.itbook.playwithds.tree;

/**
 * @author personajian
 * 树的孩子表示法
 */
public class ChildTree {
	
	/**
	 * @author personajian
	 * 孩子节点（链表）
	 */
	private static class CTNode{
		int child;
		CTNode next;
	}
	
	/**
	 * @author personajian
	 * 表头结构（有数据域data、孩子链表的头指针）
	 */
	private static class CTBox{
		int data;
		int parent;//双亲位置
		CTNode firstChild;
	}
	
	private static final int MAXTREESIZE=100;
	
	CTBox nodes[];//节点数组
	int r;//根位置
	int n;//节点数

}
