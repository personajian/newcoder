package cn.edu.seu.swordoffer;

import java.time.Period;

/**重建二叉树
 * @Author personajian
 * @Date 2017/7/25 22:58
 */
public class T6_ConstructBTree {

	private class BTreeNode{
		int value;
		BTreeNode left;
		BTreeNode right;
		
		public BTreeNode() {
			this.value=0;
			this.left=null;
			this.right=null;
		}
		
		public BTreeNode(int value, BTreeNode left, BTreeNode right) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	/**根据二叉树的前序遍历和中序遍历，重建二叉树。
	 * @param preoder前序遍历序列
	 * @param inorder中序遍历序列
	 * @param length二叉树节点数
	 * @return
	 * @throws Exception 
	 */
	public BTreeNode Construct(int[] preOder,int[] inOrder) {
		if(preOder==null||inOrder==null||preOder.length!=inOrder.length) return null;

		return ConstructCore(preOder,0,preOder.length-1,inOrder, 0, inOrder.length-1);
	}

	private BTreeNode ConstructCore(int[] preOrder,int startPre,int endPre,int[] inOrder,int startIn,int endIn) {
		
		if ((endPre - startPre) != (endIn - startIn))
			return null;
		if (preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0)
			return null;	
		
		//前序遍历序列的第一个数字是根节点
		int rootValue=preOrder[startPre];
		BTreeNode root=new BTreeNode();
		root.value=rootValue;

		//只有一个元素时，返回该节点
		if(startPre==endPre&&startIn==endIn) return root;
		
		int rootIndex=-1,count=0;
		//在中序遍历中找到根节点的值
		for(int i=startIn;i<=endIn;i++,count++) {
			if(rootValue==inOrder[i]) {
				rootIndex=i;
				break;
			}
		}
		
		//没有在中序遍历中找到该节点
		if(rootIndex==-1) return null;
	
		if(count>0) {
			//构建左子树
			root.left=ConstructCore(preOrder, startPre+1, startPre+count, inOrder, startIn, rootIndex-1);
		}
		
		if(endPre>startPre+count) {
			//构建右子树
			root.right=ConstructCore(preOrder, startPre+count+1, endPre, inOrder, rootIndex+1, endIn);
		}
		
		return root;
	}
	
	 public static void main(String[] args) {  
		T6_ConstructBTree binTree = new T6_ConstructBTree();
		int[] preOrder1 = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inOrder1 = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };

		int[] preOrder2 = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inOrder2 = new int[] { 8, 6, 5, 3, 7, 4, 2, 1 };
		BTreeNode root = binTree.Construct(preOrder1, inOrder1);
		System.out.println("普通二叉树 ，结果：" + root);
		root = binTree.Construct(preOrder2, inOrder2);
		System.out.println("只有左节点的二叉树 ，结果：" + root);
	    }  
}
