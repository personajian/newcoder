package cn.edu.seu.itcompany.toutiao;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int COUNT = 100000;
			Member[] stack = new Member[COUNT];
			for (int j = 0; j < stack.length; j++) {
				stack[j]= new Member();
			}
			int top;
			int num, n;
			long maxInternalValue, temp, value;
			int count;
			n = in.nextInt();
			top = 0;
			maxInternalValue = 0;
			for (int i = 0; i < n; ++i) {
				num = in.nextInt();
				temp = 0;
				count = 0;
				while (top > 0 && stack[top - 1].width >= num) {
					stack[top - 1].count += count;
					value = (stack[top - 1].height + temp) * stack[top - 1].width;
					if (value > maxInternalValue) {
						maxInternalValue = value;
					}
					temp += stack[top - 1].height;
					count = stack[top - 1].count;
					--top;
				}
				stack[top].height = num + temp;
				stack[top].width = num;
				stack[top].begin = i + 1 - count;
				stack[top].count = 1 + count;
				++top;
			}
			temp = 0;
			count = 0;
			while (top > 0) {
				stack[top - 1].count += count;
				value = (stack[top - 1].height + temp) * stack[top - 1].width;
				if (value > maxInternalValue) {
					maxInternalValue = value;
				}
				temp += stack[top - 1].height;
				count = stack[top - 1].count;
				--top;
			}
			System.out.println(maxInternalValue);
		}
		in.close();
	}
}

class Member {
	long height;
	long width;
	int begin;
	int count;
};
