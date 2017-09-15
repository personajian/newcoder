package cn.edu.seu.itcompany.alibaba.ioc;

public class Singleton {
	private static Singleton instacnce=new Singleton();

	private Singleton() {};

	public static Singleton getInstance(){
		return instacnce;
	}

}
