package cn.edu.seu.swordoffer;

/**一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @Author personajian
 * @Date 2017/8/19 19:02
 */
public class T9_JumpFloorII {
    public int JumpFloorII(int target) {
        if(target==1)
            return 1;

        int sum=1;
        for(int i=2;i<=target;i++){
            sum*=2;
        }
        return sum;
    }
}