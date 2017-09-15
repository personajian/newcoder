package cn.edu.seu.itcompany.aqiyi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static int result = 0;

	private static int counter = Integer.MIN_VALUE;

	private static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			String str = in.next();

			result = solve(str);

			System.out.println(result);
		}
		in.close();
	}

	private static int solve(String str) {

		int count = 0;
		char[] flags = str.toCharArray();
		
		if (!str.isEmpty()) {

			for (int i = 0; i < flags.length; i++) {
				if ('(' == flags[i])
					count++;
				else
					map.put(i, count--);

			}

			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if (entry.getValue() > counter)
					counter = entry.getValue();
			}

			return counter;
		} else
			return 0;

	}

}
