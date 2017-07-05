package cn.edu.seu.itbook.deepinjvm;

/**
 * @author personajian
 * 虚拟机决定了方法的接受者实际类型是Son->动态分派是单分派类型！
 */
public class Dispatch {
	static class QQ{}
	static class _360{}
	
	public static class Father{
		public void hardChoice(QQ arg) {
			System.out.println("father choose qq");
		}
		
		public void hardChoice(_360 arg) {
			System.out.println("father choose 360");
		}
		
	}
	
	public static class Son extends Father{
		public void hardChoice(QQ arg) {
			System.out.println("son choose qq");
		}
		
		public void hardChoice(_360 arg) {
			System.out.println("son choose 360");
		}
	}
	
	public static void main(String[] args) {
		Father father= new Father();
		Father son =new Son();
		father.hardChoice(new _360());//father choose 360
		son.hardChoice(new QQ());//son choose qq:虚拟机决定了方法的接受者实际类型是Son->动态分派是单分派类型！
	}
}
