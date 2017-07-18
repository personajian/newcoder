package cn.edu.seu.itcompany.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountChar {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str=in.next();
			char[] ch=str.toCharArray();
			Map<Character, Boolean> map=new HashMap<Character,Boolean>();
			for(char c:ch) {
				if(map.containsKey(c)) map.put(c,true);
			}
			System.out.println(map.size());
		}
		in.close();
	}
}
