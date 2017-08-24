package cn.edu.seu.itcompany.toutiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Sort {

	public static void main(String[] args) {
		int[] array= {5,9,6,4,1,2,3,8,7};
		
		List<Integer> list=new ArrayList<>();
		
		for(int i:array)
			list.add(i);
		
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				int differ=o1-o2;
				return differ<0?1:-1;
			}
		});
		
		
		for(int i:list)
			System.out.println(i);
		
	}

}
