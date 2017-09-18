package cn.edu.seu.swordoffer;

/**
 * 平衡二叉树
 *
 * @Author personajian
 * @Date 2017/7/25 13:42
 */
public class IsBalancedTree {


    public boolean isBalancedCore(TreeNode root) {
        int depth = 0;
        return isBalancedCore(root, depth);
    }

    private boolean isBalancedCore(TreeNode root, int depth) {
        //递归基：到达了叶子节点
        if (root == null) {
            depth = 1;//重点：记录节点高度
            return true;//是否平衡

        }
        //非叶子节点
        //左子树高度，右子树高度：会在递归返回时，依次求出！
        int ldepth = 0, rdepth = 0;

        if (isBalancedCore(root.left, ldepth) && isBalancedCore(root.right, rdepth)) {
            int differ = Math.abs(ldepth - rdepth);
            if (differ <= 1) {
                //重点：记录高度！
                depth = ldepth > rdepth ? ldepth : rdepth;
                return true;
            }
        }

        return false;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        TreeNode p = root;

        if (p == null) return true;
        else {
            if (Math.abs(TreeDepth(p.left) - TreeDepth(p.right)) > 1) {
                return false;
            }
            return IsBalanced_Solution(p.left) || IsBalanced_Solution(p.right);
        }
    }

    public int TreeDepth(TreeNode root) {
        TreeNode p = root;
        if (p == null) return 0;
        else
            return Math.max(TreeDepth(p.left), TreeDepth(p.right)) + 1;
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
