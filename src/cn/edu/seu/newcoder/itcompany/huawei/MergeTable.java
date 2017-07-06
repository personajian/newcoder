package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MergeTable {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num=in.nextInt();
			Map<Integer, Integer> table=new TreeMap<Integer,Integer>();
			for(int i=0;i<num;i++) {
				int key=in.nextInt();
				int value=in.nextInt();
				if(table.containsKey(key)) {
					table.put(key, (int) table.get(key)+value);
				}else table.put(key, value);
			}
			for(Integer k:table.keySet()) {
				System.out.println(k+" "+table.get(k));
			}
		}
		in.close();
	}
}
