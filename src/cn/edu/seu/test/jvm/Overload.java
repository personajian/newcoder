package cn.edu.seu.test.jvm;

import java.io.Serializable;

/**
 * @author personajian
 * 《深入理解JVM》page247
 * 虚拟机字节码执行引擎-方法调用-静态分派-重载方法匹配优先级。
 */
public class Overload {
	
	public static void sayHello(){
		System.out.println("hello Object");
	}
	
	/*public static void sayHello(int arg){
		System.out.println("hello int");
	}*/
	
	/*public static void sayHello(long arg){
		System.out.println("hello long");
	}*/
	
	/*public static void sayHello(Character arg){
		System.out.println("hello Character");
	}*/
	
	/*public static void sayHello(char arg){
		System.out.println("hello char");
	}*/
	
	public static void sayHello(char... arg){
		System.out.println("hello char...");
	}
	
	/*public static void sayHello(Serializable arg){
		System.out.println("hello Serializable");
		
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sayHello('a');
	}

}
