package cn.edu.seu.itcompany.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author personajian
 *题目描述
	击鼓传花：有若干男、女生一起玩击鼓传花的游戏，游戏规则如下：
	1. 男女生按照顺时针面向内围成一圈，女生和男生分别按照身高顺时针排序，女生在男生前面；
	2. 从身高最矮的女生开始第一轮游戏，参与者按照鼓点依次传花，鼓点结束所在的选手淘汰，从淘汰者下个选手开始下一轮传花，直到剩下最后一位胜出；
	注意：
	身高单位为cm, 范围为（100，200）的整数，每位参与者特征为（ID，性别，身高）;
	为方便运算，每次传花鼓点数是固定的；
	如果遇到性别和身高相同的成员，后加入的成员排在前面;
	题目输入参数为所有参与者特征信息，输出最后获胜选手ID;
	输入描述:
	输入第一行包括一个整数n,即参加的人数
	从第二行开始的n行包括n个参与成员的信息。包括ID,性别,身高。
	最后一行包括一个整数,即固定的鼓点
	格式如样例所示。
	
	
	输出描述:
	最终获胜的成员ID（整型）
	
	输入例子:
	3
	1 MALE 170
	2 FEMALE 180
	3 MALE 190
	2
	
	输出例子:
	3

 */
public class Fxone {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int count = in.nextInt();
			List<Person> persons = new ArrayList<>(count);

			for (int i = 0; i < count; i++) {
				Person person = new Person();
				person.setId(in.nextInt());
				if (in.next().equals("MALE"))
					person.setSex(1);
				else
					person.setSex(0);
				person.setLength(in.nextInt());
				persons.add(person);
			}

			int point = in.nextInt();

			PComparator pComparator = new PComparator();
			//对读入 的persons进行自定义排序。
			Collections.sort(persons, pComparator);
			
			//将排好序的persons放入链表之中。
			List<Person> personsLink = new LinkedList<>(persons);

			Iterator<Person> it = personsLink.iterator();

			int i = 0;
			//传花就是循环遍历链表
			while (it.hasNext()) {
				
				it.next();
				i++;
				if (i == point){//遇到鼓点，此人移除链表，索引重置为0。
					it.remove();
					i=0;
				}
				//链表中只剩一个人时候输出，算法结束。
				if (personsLink.size() == 1) {
					System.out.println(it.next().getId());
					return;
				} 
				//链表中超过两个人时候，当遍历到链表尾部时，需要更新iterator为表头。	
				else if (!it.hasNext() && personsLink.size() > 1)
					it = personsLink.iterator();
			}

		}

	}

}


/**
 * @author personajian
 * 自定义comparator：比较优先级sex>length>id。
 */
class PComparator implements Comparator<Person>{

	  @Override
	  public int compare(Person o1, Person o2) {
		if(o1.getSex()<o2.getSex()) return -1;
		else if(o1.getSex()==o2.getSex()){
			if(o1.getLength()<o2.getLength()) return -1;
			else if(o1.getLength()==o2.getLength()){
				if(o1.getId()<=o2.getId()) return -1;
				else return 1;
			}else return 1;
		}else return 1;
	  }
}


/**
 * @author personajian
 * 模型：人
 */
class Person{
	private int id;
	private int sex;
	private int length;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
}
