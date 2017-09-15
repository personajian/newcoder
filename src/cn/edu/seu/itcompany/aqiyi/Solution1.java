package cn.edu.seu.itcompany.aqiyi;
import java.util.*;

public class Solution1 {
	
	private static String result;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			String X1 = in.next();
			int K1 = in.nextInt();
			String X2 = in.next();
			int K2 = in.nextInt();
			
			result=solve(X1,K1,X2,K2);
			
			System.out.println(result);
			


		}
		in.close();
	}

	private static String solve(String X1, int K1, String X2, int K2) {
		
		String result= "";
		String count1 = "";
		String count2 = "";
		int res;
		
		int l1 = X1.length() * K1;
		int l2 = X2.length() * K2;
		if(l1 > l2) {
			return "Greater";
		}else if(l1 < l2) {
			return "Less";
		} else {
			for(int i = 0; i < K1; i++) {
				count1 += X1;
			}
			
			for(int i = 0; i < K2; i++) {
				count2 += X2;
			}
			
			res = count1.compareTo(count2);
			
			if(res > 0) {
				return "Greater";
			}else if(res < 0) {
				return "Less";
			}else {
				return "Equal";
			}
		}		
	}
}
