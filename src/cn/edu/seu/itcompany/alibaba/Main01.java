package cn.edu.seu.itcompany.alibaba;

import java.util.Scanner;

public class Main01 {
	/** 请完成下面这个process函数，实现题目要求的功能 **/
	/** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^ **/
	
	private static final int CUSTOMS_LIMIT_MONEY_PER_BOX=2000;
	
	private static int process(Model[] items,int itemNum) {
		/*for(Model item:items) {
			if(item.price<2000)
				return 5;
		}*/
		return 3;
	}

	private static class Model{
		int price;
		int length;
		int width;
		int height;
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Model boxTemplate=new Model();
		boxTemplate.price = CUSTOMS_LIMIT_MONEY_PER_BOX;
		int boxMinNum = Integer.MAX_VALUE;
			
		while (scanner.hasNext()) {
			boxTemplate.length = scanner.nextInt();
			boxTemplate.width = scanner.nextInt();
			boxTemplate.height = scanner.nextInt();

			int itemNum = scanner.nextInt();
			Model[] items = new Model[itemNum];
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
			System.out.println(process(items,itemNum));

		}
	}

}