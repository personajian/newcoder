package cn.edu.seu.swordoffer;

/**旋转数组的最小值
 * @Author personajian
 * @Date 2017/7/25 21:18
 */
public class T8_MinNum {
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0) return 0;
        for(int i=0;i<array.length;i++){
            if(array[i]>array[i+1]) return array[i+1];
        }
        return array[0];
    }
}