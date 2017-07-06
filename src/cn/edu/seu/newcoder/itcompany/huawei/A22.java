package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Scanner;

public class A22 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String string=in.next();
			decode(string);
		}
		in.close();
	}

	private static String[] patterns = { "F", "G", "R", "S", "T", "L", "M", "N", "O", "P", "Q", "W", "X", "Y", "Z", "U",
			"A", "G", "H", "I", "J", "K", "B", "C", "D", "E", "l", "m", "n", "o", "p", "i", "j", "k", "f", "g", "h",
			"a", "b", "c", "d", "e", "q", "r", "w", "x", "y", "z", "s", "t", "u", "v" };
	
	public static void decode(String string){
		if(string==null||string.length()==0)
			return ;
		for(int i=0;i<string.length();i++){
            char c=string.charAt(i);
            if(c!='-'&&c!='.'&&c!='#'){
            	System.out.println("ERROR");
                return ;
            }
        }
		String[] strs=string.split("#");
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<strs.length;i++){
			StringBuilder sb1=new StringBuilder();
			for(int k=0;k<strs[i].length();k++){
				if(strs[i].charAt(k)=='-')
					sb1.append("0");
				else
					sb1.append("1");
			}
			String tmp=sb1.toString();
			if(tmp.equals(""))
                continue;
			int index=0,count=0;;
			for(int j=tmp.length()-1;j>=0;j--){
				if(tmp.charAt(j)=='1'){
					index+=Math.pow(2, count);
				}
				count++;
			}
			if(index>=52){
				System.out.println("ERROR");
				return ;
			}
			sb.append(patterns[index]);
		}
		System.out.println(sb.toString());
	}

}
