package cn.edu.seu.test.jvm;

/**
 * @author personajian
 * 《深入理解JVM》page247
 * 虚拟机字节码执行引擎-方法调用-静态分派（与重载相关）
 * Human man =new Man():Human是静态类型（编译器可知）；Man是实际类型（运行期才可知）。编译器在编译阶段并不知道一个对象的实际类型是什么。
 * 虚拟机（编译器）在重载时是通过参数的静态类型而不是实际类型作为判断依据的。
 */
public class StaticDispatch {
	static abstract class Human{
		
	}
	
	static class Man extends Human{

	}
	
	static class Woman extends Human{
		
	}
	
	/**重载时是通过参数的静态类型Human来决定重载版本的。
	 * @param guy
	 */
	public void sayHello(Human guy) {
		System.out.println("hello,guy!");
	}
	
	public void sayHello(Man guy) {
		System.out.println("hello,gentleman!");
	}
	
	public void sayHello(Woman guy) {
		System.out.println("hello,lady!");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Human man=new Man();//静态类型是Human；实际类型是Man
		Human woman=new Woman();//静态类型是Human；实际类型是Woman
		StaticDispatch sr=new StaticDispatch();
		sr.sayHello(man);//方法调用过程中使用了静态分派，传入的参数类型是静态类型Human，而不是实际类型Man。
		sr.sayHello(woman);
	}

}
