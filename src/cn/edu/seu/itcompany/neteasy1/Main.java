package cn.edu.seu.itcompany.neteasy1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String string = in.nextLine();
			int len=string.length();
			int max = Integer.MIN_VALUE;
			int ans = 0;
			int answer = 0;

			int n = len / 2;

			List<String> list = solute(n);
			
			List<Integer> list2 = new ArrayList<Integer>();
			

			for(int i = 0;i < list.size();i ++) {
				if(!list.get(i).equals(string)) {
					ans = ans(string,list.get(i));
					list2.add(ans);
					if(ans > max) {
						max = ans;
					}
				}
			}
			
			for(int i = 0;i < list2.size();i ++) {
				if(list2.get(i) == max) {
					answer ++;
				}
			}
			System.out.println(answer);
		}
		in.close();
	}
	
    public static int compute(char[] str1, char[] str2) {
        int sub1 = str1.length;
        int sub2 = str2.length;
 
        int[][] a = new int[sub1 + 1][sub2 + 1];
 
        for (int i = sub1 - 1; i >= 0; i--)
        {
            for (int j = sub2 - 1; j >= 0; j--)
            {
                if (str1[i] == str2[j])
                    a[i][j] = a[i + 1][j + 1] + 1;
                else
                    a[i][j] = Math.max(a[i + 1][j], a[i][j + 1]);
            }
        }
 
        return a[0][0];
    }
	
    public static void solve(List<String> list,String s,int left,int right){
        if(left > right){
            return;
        }
        if(left > 0){
            solve(list,s + "(",left - 1,right);
        }
        if(right > 0){
            solve(list,s + ")",left,right - 1);
        }
        if(left == 0 && right == 0){
            list.add(s);
        }
    }
    
    public static List<String> solute(int n) {
        List<String> list = new ArrayList<String>();
        solve(list,"",n,n);
        return list;
    }
	

    
    public static int ans(String str1, String str2)
    {
        return compute(str1.toCharArray(), str2.toCharArray());
    }
    

}
