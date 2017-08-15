package cn.edu.seu.swordoffer;
/**二叉搜索树的后序遍历序列
 * 有两个性质：后序+搜索
 * 后序遍历序列的最后一个树是根节点；
 * 搜索树的左子树小，右子树大。
 * @Author personajian
 * @Date 2017/8/15 22:13
 */
public class T24_VerifySquenceOfBST {
    /**递归解法，但牛客网函数要求：public boolean T24_VerifySquenceOfBST(int [] sequence)
     * @Param
     * @Return
     */

    public static void main(String[] args) {
        int[] sequence={4,6,7,5};
        System.out.println(verifySquenceOfBST(sequence));
    }

    public static boolean verifySquenceOfBST(int [] sequence) {

        if(sequence.length==0) return false;
        if(sequence.length==1) return true;

        return isSeqOfBst(sequence,0, sequence.length-1);

    }

    private static boolean isSeqOfBst(int [] sequence,int start,int end){

        if(start>=end)
            return true;

        int root = sequence[end];

        int i=start;
        for(;i<=end;++i){
            if(sequence[i]>root)
                break;
        }

        int j=i;
        for(;j<=end;++j){
            if(sequence[j]<root)
                return false;
        }

        boolean left=true;
        if(i>=0)
            left=isSeqOfBst(sequence,start,i-1);

        boolean right=true;
        if(i<=end-1)
            right=isSeqOfBst(sequence,i,end-1);

        return (left&&right);

    }
}