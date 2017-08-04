package cn.edu.seu.itcompany.pinduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**求无序数组三个数的最大乘积
 * @Author personajian
 * @Date 2017/8/2 21:35
 */
public class Solution1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num=in.nextInt();
			int[] array=new int[num];
			for(int i=0;i<num;i++) {
				array[i]=in.nextInt();
			}
			int maxMulti=maxMulti(array,num);
			System.out.println(maxMulti);
		}
		in.close();
	}

	private static int maxMulti(int[] array,int num) {
		Arrays.sort(array);
		int max1=array[num-1]*array[num-2]*array[num-3];
		int max2=array[0]*array[1]*array[num-3];
		
		
		return max1>max2?max1:max2;
	}
}
