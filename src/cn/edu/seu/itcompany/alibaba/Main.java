package cn.edu.seu.itcompany.alibaba;

import java.util.Scanner;

public class Main {
	/** 请完成下面这个process函数，实现题目要求的功能 **/
	/** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^ **/
	private static final int CUSTOMS_LIMIT_MONEY_PER_BOX = 2000;

	static class BoxTemplate {
		int length;
		int width;
		int height;
		int price;
	}

	static class Model {
		int length;
		int width;
		int height;
		int price;
	}

	static BoxTemplate boxTemplate = new BoxTemplate();
	static Model[] items = null;
	static int boxMinNum;

	private static int process() {
		int boxNums = 1;
		int remainingLength = boxTemplate.length;
		int remainingWidth = boxTemplate.width;
		int remainingHeight = boxTemplate.height;
		for (Model item : items) {
			if (item.price > 2000 || item.length > boxTemplate.length || item.height > boxTemplate.height
					|| item.width > boxTemplate.width) {
				return -1;
			}

			// 如果横放、竖放跟侧放中的一个可以，就boxNums+1;
			if (item.length < remainingLength && item.width < remainingWidth && item.height < remainingHeight) {
				// 横放
				remainingLength -= item.length;
				remainingWidth -= item.width;
				remainingHeight -= item.height;
			} else if (item.length < remainingWidth && item.width < remainingWidth && item.height < remainingHeight) {
				// 竖放
				remainingLength -= item.width;
				remainingWidth -= item.length;
				remainingHeight -= item.height;
			} else if (item.length < remainingHeight && item.width < remainingWidth && item.height < remainingHeight) {
				// 侧放
				remainingLength -= item.height;
				remainingWidth -= item.width;
				remainingHeight -= item.length;
			} else {
				boxNums++;
				remainingLength = boxTemplate.length;
				remainingWidth = boxTemplate.width;
				remainingHeight = boxTemplate.height;
			}
		}
		return boxNums;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		boxTemplate.price = CUSTOMS_LIMIT_MONEY_PER_BOX;

		while (scanner.hasNext()) {
			boxTemplate.length = scanner.nextInt();
			boxTemplate.width = scanner.nextInt();
			boxTemplate.height = scanner.nextInt();

			int itemNum = scanner.nextInt();
			items = new Model[itemNum];
			for (int i = 0; i < itemNum; i++) {
				Model item = new Model();
				item.price = scanner.nextInt();
				item.length = scanner.nextInt();
				item.width = scanner.nextInt();
				item.height = scanner.nextInt();
				items[i] = item;
			}
			long startTime = System.currentTimeMillis();
			boxMinNum = Integer.MAX_VALUE;
			System.out.println(process());

		}
	}

}
