package cn.edu.seu.itcompany.huawei;

import java.util.*;

/**
 * @author personajian
 * [编程|300分] 函数循环调用
	时间限制：3秒
	空间限制：32768K
	题目描述
	在软件设计时要极力避免函数的循环调用。请判断两个函数之间是否存在循环调用关系。 如果函数A调用或者间接调用B，同时B调用或者间接调用A，则A和B存在循环调用关系。间接调用是指如A调用B，B调用C，则A间接调用C。
	例如，函数之间的调用关系如下：A调用B，B调用C，C调用A，则A和B存在循环调用关系。
	说明：
	1）函数名区分大小写。
	2）函数名长度不超过256字符。
	3）调用关系是单向的，A调用B，B不一定调用A。
	4）函数自己调用自己，也算是循环调用。
	
	输入描述:
	输入由N+2行文本组成
	
	第一行是一个整数，表示调用关系的个数N。
	
	后续N行，每行两个函数名，表示一组调用关系，调用者和被调用者由一个空格分开，如“A B”表示A调用B。
	
	最后一行是要判断是否存在循环调用关系的两个函数，由一个空格分开。
	
	
	输出描述:
	判断两个函数之间是否存在循环调用关系，存在输出字母T，不存在输出字母F
	
	输入例子:
	3
	A B
	B C
	C A
	A B
	
	输出例子:
	T

 */
public class SpecialOffer3 {

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
