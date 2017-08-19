package cn.edu.seu.itcompany.pinduoduo;

import java.util.ArrayList;

/**  Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
     Output: [20,24]
     Explanation:
     List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
     List 2: [0, 9, 12, 20], 20 is in range [20,24].
     List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * @Author personajian
 * @Date 2017/8/18 19:28
 */

public class Snippet4 {
	
	public static void main(String[] args) {
		int k=3;
		ArrayList<Integer> a1= new ArrayList<>();
		ArrayList<Integer> a2= new ArrayList<>();
		ArrayList<Integer> a3= new ArrayList<>();
		a1.add(1);
		a1.add(3);
		a1.add(5);
		a2.add(4);
		a2.add(8);
		a3.add(2);
		a3.add(5);
		
		ArrayList<ArrayList<Integer> > arr=new ArrayList<>();
		arr.add(a1);
		arr.add(a2);
		arr.add(a3);
		
		ArrayList<Integer> interval=minInterval(k,arr);
		
		for(int i:interval) {
			System.out.print(i+" ");
		}
		
		
	}
	
	public static ArrayList<Integer> minInterval(int k,ArrayList<ArrayList<Integer> > arr){
		
		//存放区间两端的值
		ArrayList<Integer> interval=new ArrayList<>(2);
		
		//存放各个数组的最小值，最大值
		int[] minArr=new int[k];
		int[] maxArr=new int[k];
		
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		
		//记录整个二维数组的最大值最小值
		for (int i = 0; i < k; i++) {
			minArr[i]=arr.get(i).get(0);
			maxArr[i]=arr.get(i).get(arr.get(i).size()-1);
			if(minArr[i]<min)
				min=minArr[i];
			if(maxArr[i]>max)
				max=maxArr[i];
		}
		
		
		boolean flag1=true;
		boolean flag2=true;		
		boolean flag11=true;
		boolean flag22=true;
		//核心逻辑
		while(true) {
					
			//区间两端值，是否在各个数组中
			for (int i = 0; i < k; i++) {
				flag1=flag1&&arr.get(i).contains(min);
				flag2=flag2&&arr.get(i).contains(max);
				int min_=min+1;
				int max_=max-1;
				flag11=flag11&&arr.get(i).contains(min_);
				flag22=flag22&&arr.get(i).contains(max_);
			}
			
			if(flag1&&flag2&&flag11&&!flag22) {
				max--;
			}
				
			if(flag1&&flag2&&!flag11&&flag22) {
				min++;
			}
			
			if(flag1&&flag2&&flag11&&flag22) {
				max++;
				min--;
			}
			
			//当区间两端值，不在各个数组时，跳出循环
			if(flag1&&flag2&&!flag11&&!flag22) {
				interval.add(max);
				interval.add(min);
				break;
			}			
		}	
		
		return interval;
	
		}
	
}

