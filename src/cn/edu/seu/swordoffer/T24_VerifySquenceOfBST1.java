package cn.edu.seu.swordoffer;
/**二叉搜索树的后序遍历序列
 * 有两个性质：后序+搜索
 * 后序遍历序列的最后一个树是根节点；
 * 搜索树的左子树小，右子树大。
 * @Author personajian
 * @Date 2017/8/15 22:13
 */
public class T24_VerifySquenceOfBST1 {
    /**递归解法，但牛客网函数要求：public boolean T24_VerifySquenceOfBST(int [] sequence)
     * @Param
     * @Return
     */

    public static void main(String[] args) {
        int[] sequence={4,6,7,5};
        System.out.println(verifySquenceOfBST(sequence));
    }

    public static boolean verifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0)
            return false;
        if (sequence.length == 1)
            return true;
        return ju(sequence, 0, sequence.length - 1);

    }

    public static boolean ju(int[] a, int star, int root) {
        if (star >= root)
            return true;
        int i = root;
        //从后面开始找
        while (i > star && a[i - 1] > a[root])
            i--;//找到比根小的坐标
        //从前面开始找 star到i-1应该比根小
        for (int j = star; j < i - 1; j++)
            if (a[j] > a[root])
                return false;
        ;
        return ju(a, star, i - 1) && ju(a, i, root - 1);
    }
}