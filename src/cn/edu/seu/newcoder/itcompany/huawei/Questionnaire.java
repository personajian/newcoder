package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @author personajian
 *
 *明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作。 
 */
public class Questionnaire {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		while(in.hasNext()){
			int number=in.nextInt();
			int array[] =new int[number];
			int arrayResult[] = new int[number];
			int arlength=0;
			
			for(int i=0;i<number;i++){
				array[i]=in.nextInt();
				}
			Arrays.sort(array);

			System.out.println(array[0]);
			for(int i=1;i<number;i++){
				if(array[i]!=array[i-1]){
					System.out.println(array[i]);
				}
			}
		}
	}

}
