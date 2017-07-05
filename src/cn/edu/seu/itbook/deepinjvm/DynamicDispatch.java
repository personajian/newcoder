package cn.edu.seu.itbook.deepinjvm;

/**
 * @author personajian
 * 《深入理解JVM》page249
 * 虚拟机字节码执行引擎-方法调用-动态分派（与重写相关）：运行期根据实际类型确定方法执行版本。
 * 原因在于invokevirtual指令的多态查找
 */
public class DynamicDispatch {

	static abstract class Human{
		protected abstract void sayHello();
	}
	
	static class Man extends Human{
		@Override
		protected void sayHello() {
			System.out.println("man say hello");
			
		}

	}
	
	static class Woman extends Human{
		@Override
		protected void sayHello() {
			System.out.println("woman say hello");
		}
	}
	
	public static void main(String[] args) {
		Human man= new Man();
		Human woman=new Woman();
		man.sayHello();
		woman.sayHello();
		man=new Woman();
		man.sayHello();
	}

}
