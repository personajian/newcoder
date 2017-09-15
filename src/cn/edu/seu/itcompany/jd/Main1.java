package cn.edu.seu.itcompany.jd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.xml.bind.SchemaOutputResolver;

public class Main1 {

	public static int result = 0;;

	public static HashMap<Double, Integer> map = new HashMap<>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();

			solve(n);	


		System.out.println(result);
		}
		in.close();
	}

	private static void solve(int n) {

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (!map.containsKey(Math.pow(i, j))) {
					map.put(Math.pow(i, j), 1);
				} else
					map.put(Math.pow(i, j), map.get(Math.pow(i, j)) + 1);
			}
		}
		for (Double i : map.keySet()) {

			int k = map.get(i);
			if (k >= 2) {
				result += k * (k - 1);
			}
		}
		result = n * n + result;
	}

}
