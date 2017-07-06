package cn.edu.seu.newcoder.itcompany.huawei;

import java.util.*;

public class A3 {

	public static boolean searchDFS(Map<String, List<String>> map, String start, String end) {
		if (start.equals(end)) {
			List<String> list = map.get(start);
			for (String str : list) {
				if (str.equals(start)) {
					return true;
				}
			}
			return false;
		}
		Stack<String> stack = new Stack<String>();
		Set<String> set = new HashSet<String>();
		stack.push(start);

		boolean bool = false;
		while (!stack.isEmpty()) {
			String name = stack.peek();
			List<String> list = map.get(name);
			if (list == null || list.size() == 0) {
				stack.pop();
				continue;
			}
			boolean flag = true;
			for (String str : list) {
				if (!set.contains(str)) {
					if (str.equals(end)) {
						bool = true;
						break;
					}
					stack.push(str);
					set.add(str);
					flag = false;
				}

			}
			if (flag == true)
				stack.pop();
		}
		if (bool == false)
			return false;
		set.clear();
		stack.clear();
		stack.push(end);
		boolean f = false;
		while (!stack.isEmpty()) {
			String name = stack.peek();
			List<String> list = map.get(name);
			if (list == null || list.size() == 0) {
				stack.pop();
				continue;
			}
			boolean flag = true;
			for (String str : list) {
				if (!set.contains(str)) {
					if (str.equals(start)) {
						f = true;
						break;
					}
					stack.push(str);
					set.add(str);
					flag = false;
				}

			}
			if (flag == true)
				stack.pop();
		}
		return f;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		while (in.hasNext()) {
			int n = Integer.parseInt(in.nextLine());
			for (int i = 0; i < n; i++) {
				String[] strings = in.nextLine().split("\\ +");
				if (map.containsKey(strings[0])) {
					List<String> list = map.get(strings[0]);
					list.add(strings[1]);
				} else {
					List<String> list = new ArrayList<String>();
					list.add(strings[1]);
					map.put(strings[0], list);

				}

			}

			String[] goal = in.nextLine().split("\\ +");
			String start = goal[0];
			String end = goal[1];
			if (searchDFS(map, start, end))
				System.out.println("T");
			else
				System.out.println("F");
		}
		in.close();
	}

}
