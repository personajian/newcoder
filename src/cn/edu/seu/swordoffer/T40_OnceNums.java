package cn.edu.seu.swordoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**数组中只出现一次的数字
 * @Author personajian
 * @Date 2017/7/25 14:04
 */
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class T40_OnceNums {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        List<Integer> list=new ArrayList<>(2);
        Arrays.sort(array);
        for (int i = 0; i <array.length ; i++) {
            if(i==0){
                if(array[i]!=array[i+1]){
                    list.add(array[i]);
                }
            }else if(i==array.length-1){
                if(array[i]!=array[i-1]){
                    list.add(array[i]);
                }
            }else{
                if(array[i]!=array[i-1]&&array[i]!=array[i+1]){
                    list.add(array[i]);
                }
            }
        }
        num1[0]=list.get(0);
        num2[0]=list.get(1);
    }
}
