package cn.edu.seu.swordoffer;

import java.util.ArrayList;

/**对称二叉树：原二叉树与镜像二叉树相等
 * 中序序列对称？只能AC90%，遇到树结点值都一样的二叉树，没办法判断
 * @Author personajian
 * @Date 2017/8/18 19:42
 */
public class T59_SymmetricalTree {

    public boolean isSymmetrical(TreeNode pRoot) {

        ArrayList<TreeNode> inorder=new ArrayList<>();

        inOrder(pRoot,inorder);

        int length = inorder.size();

        int i=0,j=length-1;

        while(i<=j){
            if(inorder.get(i).val!=inorder.get(j).val)
                return false;
            i++;
            j--;
        }

        return true;
    }

    private void inOrder(TreeNode pRoot ,ArrayList<TreeNode> inorder){
        if(pRoot==null){
            // 为何添加一个标识符号会不行？
            // inorder.add(new TreeNode(-1));
            return ;
        }
        inOrder(pRoot.left,inorder);
        inorder.add(pRoot);
        inOrder(pRoot.right,inorder);
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
