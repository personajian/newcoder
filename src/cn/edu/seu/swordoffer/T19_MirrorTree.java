package cn.edu.seu.swordoffer;

/**镜像二叉树（反转二叉树）
 * @Author personajian
 * @Date 2017/7/25 22:47
 */

public class T19_MirrorTree {
    public void Mirror(TreeNode root) {
        if (root == null) {
            return ;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
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
