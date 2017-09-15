package cn.edu.seu.itcompany.didi;

import java.util.Scanner;

public class Main2 {

	private static int n=0;
	private static int k=0;
	private static int mid=0;
	private static int temp=0;
	private static int index=0;
	private static int[] arr;
	private static Scanner in;

	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		while (in.hasNext()) {
			String[] s = in.nextLine().split(" ");
			n=s.length;
			arr=new int[n];
			for (int i = 0; i < n; i++) {
				arr[i]=Integer.parseInt(s[i]);
			}
			
			k=in.nextInt();
			if(k>n)
				return;
			System.out.println(getBigK(arr,n,k));
		}
		in.close();
	}

	private static int getBigK(int[] arr, int n, int k){
		if(n <= 0 || k <= 0 || k > n){
	        return -1;
	    }
		k = n-k+1;
		int l = 0;
		int r = n-1;
		while(l <= r){
		    index = split(arr, l, r);
			if(index+1 == k){
	            return arr[index];
	        }
	        else if(index+1 < k){
			    l = index+1; 
		    }
		    else{ 
		        r = index-1;
		    }
		}
		return -1;
	} 
	
	
	private static int split(int[] arr, int low, int high){
		
		if(low > high){
	        return -1;
	    }
	    mid = (low+high)>>1;
		
		temp=arr[mid];
		arr[mid]=arr[high];
		arr[high]=temp;
		
		int index = low;
		for(int i = low; i < high; i++){
		    if(arr[i] < arr[high]){
	           if(i > index){
	        	   temp=arr[i];
	        	   arr[i]=arr[index];
	        	   arr[index]=temp;
	   		   }
	   		   ++index;
			}
	    } 
		
		temp=arr[index];
		arr[index]=arr[high];
		arr[high]=temp;		
		
		return index; 
	}
	
}
