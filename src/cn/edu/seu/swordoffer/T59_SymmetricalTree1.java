package cn.edu.seu.swordoffer;

/**对称二叉树：原二叉树与镜像二叉树相等
 * 中序序列对称？只能AC90%，遇到树结点值都一样的二叉树，没办法判断
 * 剑指offer解法：原二叉树的前序遍历，肯定跟对称二叉树的前序遍历一模一样；全是相同值的二叉树，利用插入null标识
 * @Author personajian
 * @Date 2017/8/18 19:42
 */
public class T59_SymmetricalTree1 {

    public boolean isSymmetrical(TreeNode pRoot) {

        return isSymmetrical(pRoot,pRoot);
    }

    /**递归解法
     * @Param
     * @Return
     */
    private boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2){
        if(pRoot1==null&&pRoot2==null)
            return true;
        if(pRoot1==null||pRoot2==null)
            return false;

        if(pRoot1.val!=pRoot2.val)
            return false;

        return isSymmetrical(pRoot1.left,pRoot2.right)&&isSymmetrical(pRoot1.right,pRoot2.left);
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
