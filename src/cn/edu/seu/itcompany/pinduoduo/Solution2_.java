package cn.edu.seu.itcompany.pinduoduo;

import java.util.Scanner;

public class Solution2_ {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int n = in.nextInt();
			long[] array =new long[n];
			for (int i = 0; i < n; i++) {
				array[i] = in.nextLong();
			}			
						
			System.out.println(result(array));

		}
		in.close();

	}
	
	public static long result(long[] array) {
		
		
	    long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE, min1 = Long.MAX_VALUE, min2 = Long.MAX_VALUE;
	    for (long n : array) {
	        if (n > max1) {
	            max3 = max2;
	            max2 = max1;
	            max1 = n;
	        } else if (n > max2) {
	            max3 = max2;
	            max2 = n;
	        } else if (n > max3) {
	            max3 = n;
	        }

	        if (n < min1) {
	            min2 = min1;
	            min1 = n;
	        } else if (n < min2) {
	            min2 = n;
	        }
	    }
	    return Math.max(max1 * max2 * max3, max1 * min1 * min2);
	}

}
