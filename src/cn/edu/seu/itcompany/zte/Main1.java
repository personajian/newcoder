package cn.edu.seu.itcompany.zte;


import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.eclipse.jdt.internal.compiler.apt.model.IElementInfo;


public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in=new Scanner(System.in);
		int num=in.nextInt();
		int shotDegrade=in.nextInt();
		int remDegrade=in.nextInt();
		int[] health=new int[num];
		for (int i = 0; i < num; i++) {
			health[i]=in.nextInt();
		}
		
		int count=minimumNumberOfGunShots(num, shotDegrade, remDegrade, health);
		System.out.println(count);

	}
	
	public static int minimumNumberOfGunShots(int num,int shotDegrade,int remDegrade,int[] health) {
		
		if(num<1||num>20000)
			return -1;
		
		PriorityQueue<Integer> queue=new PriorityQueue<>();
		
		for (int i = 0; i < health.length; i++) {
			queue.add(health[i]);
		}
		
		
		
		//Arrays.sort(health);
		
		int n=num;
		//int lastMax=num-1;
		int result=0;
		
		for (int i = 0; i < health.length; i++) {
			if(health[i]==0)
				n--;
		}

		while(n>0) {
			result++;
			int max=queue.poll();
			max-=shotDegrade;
			if(max<=0)
				n--;
			else
				queue.add(max);
			
			Iterator<Integer> it=queue.iterator();
			
			if(it.hasNext()) {
				int temp=it.next();
				if(temp>0) {
					temp-=remDegrade;
					if(temp<=0)
						n--;
				}
			}
			
			
			
			for(int i=0;i<num;i++) {
				if(health[i]>0) {
					health[i]-=remDegrade;
					if(health[i]<=0)
						n--;
				}
			}
			Arrays.sort(health);
		}
		
		return result;
	}

}
