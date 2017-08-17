package cn.edu.seu.itcompany.pinduoduo;

import java.util.ArrayList;

public class Snippet1 {
	/*
	 * 算法思想：step1：找出所有行中最多1的个数max
	 *       具体做法：从右上角开始找，如果是1，则向左+1，1的个数max+1，否则向下+1.
	 *       step2：根据这个数字确定列，遍历该列的所有行，如果该位置为1，则是其中一个结果。
	 */
	public static ArrayList<ArrayList<Integer>> solve(int[][] nums, int M, int N) {
		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int temp = 0;
		int i = 0;
		int j = N - 1;
		
		for(;i<M&&j>=0;) {
			if(nums[i][j]==0) i++;
			else {
				temp++;j++;
			}
		}

		if(temp == 0) return lists;
		
		for(int l = 0; l < M; l++) {
			if(nums[l][N - temp] == 1) {
				list.add(l+1);
				list.add(temp);
				lists.add(list);
				list = new ArrayList<Integer>();
			}
		}
		return lists;
	}

	public static void main(String[] args) {
		int[][] nums = {{0,0,0,1,1,1}, {0,1,1,1,1,1}, {0,0,0,0,1,1}, {0,1,1,1,1,1}};
		System.out.println(solve(nums, 4, 6));
	}

}
