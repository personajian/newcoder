package cn.edu.seu.itbook.playwithds.tree;

/**
 * @author personajian
 * 二叉线索存储节点结构
 */
public class BiThrTree {
	int data;
	BiThrTree lChild;
	BiThrTree rChild;
	boolean lTag;//为0时表示lChild指向左孩子；为1时表示lChild指向前驱
	boolean rTag;//为0时表示rChild指向左孩子；为1时表示rChild指向前驱
	
	
	/**
	 * 中序遍历方式来进行线索化
	 */
	public void InOrderThread() {
		BiThrTree pre = null;//全局变量，始终指向刚刚访问过的节点
		InThread(this,pre);
	}
	
	/**中序遍历方式来进行线索化：递归
	 * @param p：p是pre的后继节点
	 * @param pre：pre是p的前驱节点
	 * 就是将中序遍历的打印节点改为：线索化的功能。
	 */
	private void InThread(BiThrTree p,BiThrTree pre) {
		if(p!=null) {
			InThread(p.lChild, pre);//递归左子树线索化
			
			threading(p,pre);
			
			InThread(p.rChild, pre);//递归右子树线索化
		}
	}
	
	/**线索化
	 * @param p
	 * @param pre
	 */
	private void threading(BiThrTree p,BiThrTree pre) {
		
		if(p.lChild==null) {//没有左孩子：就将lTag标记为真，lChild指向前驱
			p.lTag=true;
			p.lChild=pre;
		}
		if(p.rChild==null) {//没有右孩子：就将rTag标记为真，rChild指向后继（当前节点p）
			pre.rTag=true;
			pre.rChild=p;
		}
		pre=p;//保持pre指向p的前驱
	}
	
	/**中序遍历 二叉线索链表表示的二叉树
	 * @param tree
	 * @return
	 */
	public boolean InOrderThreadTraverse(BiThrTree tree) {
		BiThrTree p;
		p=tree.lChild;
		while(p!=tree) {
			while(!p.lTag) {//直到找到中序遍历的首节点
				p=p.lChild;
				System.out.println(p.data);
				while(p.rTag&&p.rChild!=tree) {//直到找到p的后继节点
					p=p.rChild;
					System.out.println(p.data);
				}
				p=p.rChild;
			}
		}
		return true;
	}
}
