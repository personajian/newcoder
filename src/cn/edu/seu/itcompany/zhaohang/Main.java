package cn.edu.seu.itcompany.zhaohang;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] arr=new int[n];
			for (int i = 0; i < n; i++) {
				arr[i]=in.nextInt();
			}
			boolean win=solve(arr, n);
			
			if(n<=2)
				System.out.println("true");
			else{
				if(win)
					System.out.println("true");
				else
					System.out.println("false");
			}
		}
		in.close();
	}

	private static boolean solve(int[] arr, int n) {
		boolean win=false;
		
		ArrayList<Integer> list=new ArrayList<>();
		
		for(int i:arr)
			list.add(i);
		
		ArrayList<Integer> listA=new ArrayList<>();
		ArrayList<Integer> listB=new ArrayList<>();
				
		while(list.size()>=4) {
			if(list.get(0)<list.get(1) && list.get(0)+list.get(1)>list.get(2)+list.get(3)) {
				listA.add(list.remove(0));
				listA.add(list.remove(0));
			}else {
				listA.add(list.remove(0));
			}
			
			if(list.get(0)<list.get(1) && list.get(0)+list.get(1)>list.get(2)+list.get(3)) {
				listB.add(list.remove(0));
				listB.add(list.remove(0));
			}else {
				listB.add(list.remove(0));
			}
		}
		
		if(list.size()==3) {
				listA.add(list.remove(0));
				listA.add(list.remove(0));
				listB.add(list.remove(0));
		}else if(list.size()==2) {
			listA.add(list.remove(0));
			listA.add(list.remove(0));
		}else if(list.size()==1) {
			listA.add(list.remove(0));
		}
		
		int sumA=0;
		int sumB=0;
		
		for(int i:listA)
			sumA+=i;
		for(int i:listB)
			sumB+=i;
		
		if(sumA>sumB)
			win=true;
		else
			win=false;		
		
		
		return win;
	}

}
