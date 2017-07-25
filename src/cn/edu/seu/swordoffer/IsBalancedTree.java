package cn.edu.seu.swordoffer;

/**平衡二叉树
 * @Author personajian
 * @Date 2017/7/25 13:42
 */
public class IsBalancedTree {

    public boolean IsBalanced_Solution(TreeNode root) {
        TreeNode p=root;

        if(p==null) return true;
        else{
            if(Math.abs(TreeDepth(p.left)-TreeDepth(p.right))>1){
                return false;
            }
            return IsBalanced_Solution(p.left)||IsBalanced_Solution(p.right);
        }
    }

    public int TreeDepth(TreeNode root) {
        TreeNode p=root;
        if(p==null) return 0;
        else
            return Math.max(TreeDepth(p.left),TreeDepth(p.right))+1;
    }


    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
