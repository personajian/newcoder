package cn.edu.seu.leetcode;

public class Solution3 {

    public int lengthOfLongestSubstring(String s) {
    	int l=s.length();
    	
    	int[][] length=new int[l][l];
    	
    	initial(length);
    	
    	dp(s,length);
    	
    	print(length);
    	
    	return max(length,l);
    }
	
	private void print(int[][] length) {
		// TODO Auto-generated method stub
		for(int i=0;i<length.length;i++){
			for (int j = 0; j < length[i].length; j++) {
				System.out.print(length[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private void initial(int[][] length) {
		// TODO Auto-generated method stub
		for(int i=0;i<length.length;i++){
			for (int j = 0; j < length[i].length; j++) {
				if(i==j) length[i][j]=1;
				else length[i][j]=0;
			}
		}
	}

	private int max(int[][] length,int l) {
		// TODO Auto-generated method stub
		int maximum=0;
		for(int i=0;i<l;i++){
			for (int j = 0; j < l; j++) {
				if(length[i][j]>maximum) maximum=length[i][j];
			}
		}
		return maximum;
	}

	private void dp(String s, int[][] length) {
		for (int i = 0; i < length.length; i++) {
			for (int j = i+1; j < length[i].length; j++) {
				length[i][j] = s.substring(i,j).contains(s.substring(j, j+1)) ? length[i][j-1] : length[i][j-1] + 1;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s="abcabcbb";
		//String s="abcdefghijklmnopqrsta";
		String s="pwwkew";
		//System.out.println(s.substring(6, 8));
		
		System.out.println(new Solution3().lengthOfLongestSubstring(s));
	}

}
