package cn.edu.seu.swordoffer;

/**青蛙跳台阶：fibonacci问题的变种，不同点在于：fib(1)=1,fib(2)=2开始
 * @Author personajian
 * @Date 2017/8/19 18:56
 */
public class T9_JumpFloor {
    public int JumpFloor(int target) {

        int[] result = {1, 1};
        if (target <= 1) return result[target];

        int fib1 = 1;
        int fib2 = 1;
        int fibN = 0;

        for (int i = 2; i <= target; i++) {
            fibN = fib1 + fib2;

            fib2 = fib1;
            fib1 = fibN;
        }

        return fibN;
    }
}