package cn.edu.seu.itbook.playwithds.binarysorttree;

/**
 * @Author personajian
 * @Date 2017/9/12 14:46
 */
public class BinarySortTree {

    public static void main(String[] args) {
        int[] a = {5, 3, 2, 6, 8, 1, 9, 7, 0, 4};
        BiTNode root = null;
        for (int i : a)
            root = put(root, i);

        System.out.println(search(root, 10));
        System.out.println(search(root, 5));
        System.out.println(search(root, 12));

        delete(root,1);

        printTree(root);

    }

    /**
     * 二叉排序书的查找
     *
     * @Param
     * @Return 查找成功，返回true；查找失败，返回false。
     */
    public static boolean search(BiTNode root, int key) {
        if (root == null)
            return false;
        else if (key == root.val)
            return true;
        else if (key < root.val)
            return search(root.lchild, key);
        else
            return search(root.rchild, key);
    }

    /**
     * 二叉排序数的插入
     * 如果存在插入值，就更新；
     * 如果不存在，就将新节点插入到该子树中。
     *
     * @Param
     * @Return 子树的根节点
     * 参见重建二叉树的重建递归过程：
     * @see cn.edu.seu.swordoffer.T6_ConstructBTree
     */
    public static BiTNode put(BiTNode root, int key) {
        //递归基：新建一个新节点
        if (root == null)
            return new BiTNode(key);
        //节点插入中，很重要的一点就是：如何记录节点父结点的信息！
        //而，递归中，需要根据一下模版：才能做到在递归中保留父结点信息。
        if (key < root.val)
            root.lchild = put(root.lchild, key);
        else if (key > root.val)
            root.rchild = put(root.rchild, key);
        else
            root.val = key;

        return root;

    }

    public static boolean delete(BiTNode root, int key) {
        if (root == null)
            return false;
        else {
            if (key == root.val)
                return deleteCore(root);
            else if (key < root.val)
                return delete(root.lchild, key);
            else
                return delete(root.rchild, key);
        }
    }
    /**二叉排序树删除节点：有错误
     * @Param
     * @Return
     */
    private static boolean deleteCore(BiTNode root) {
        BiTNode p, pre;


        //有错误！！
        if (root.lchild == null)
            root = root.rchild;
        else if (root.rchild == null)
            root = root.lchild;

        else {
            pre = root;
            p = root.lchild;
            while (p.rchild != null) {
                pre = p;
                p = p.rchild;
            }
            //找到了root结点的在中序遍历的前驱
            root.val=p.val;

            pre.rchild=p.lchild;
        }

        return true;
    }

    /**
     * 二叉排序数打印
     *
     * @Param
     * @Return
     */
    public static void printTree(BiTNode root) {
        if (root == null)
            return;
        if (root.lchild != null) {
            printTree(root.lchild);
        }
        System.out.print(root.val);
        if (root.rchild != null)
            printTree(root.rchild);
    }

    private static class BiTNode {

        int val;
        BiTNode lchild;
        BiTNode rchild;

        public BiTNode(int val) {
            this.val = val;
        }
    }
}
