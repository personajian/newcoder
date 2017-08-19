package cn.edu.seu.swordoffer;


/**
 * 树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *      8                    8
 *    /  \                 / \
 *   8    7               9  2
 *  /\
 * 9  2
 *   /\
 *  4  7
 *  注意：是子结构，不是子树！
 * 算法思想：
 * 第一步：递归遍历root1中，找到与root2根结点值相等的结点；
 *          若找到了，就再递归判断子结点是否相等
 * 也是有两个递归函数！
 * @Author personajian
 * @Date 2017/8/19 15:30
 */
public class T18_HasSubtree {
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {

        boolean result=false;

        if(root1!=null&&root2!=null){
            if(root1.val==root2.val)
                result=doesHasSubtree(root1,root2);
            if(!result)
                result= hasSubtree(root1.left,root2);
            if(!result)
                result= hasSubtree(root1.right,root2);
        }
        return result;
    }


    private boolean doesHasSubtree(TreeNode root1,TreeNode root2){
        //root2为空就可以返回true，说明root2已经是root1的子结构（不一定是子树）
        if(root2==null)
            return true;
        //root2不为空，root1已为空，说明不是子结构。
        if(root1==null)
            return false;
        //若root1 root2都不为空，并且值不等，说明不是子结构。
        if(root1.val!=root2.val)
            return false;
        //递归调用，判断子树是否相等
        return doesHasSubtree(root1.left,root2.left)&&doesHasSubtree(root1.right,root2.right);
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}