package cn.edu.seu.demo.singleton;

/*
* 双重检查锁实现懒汉单例的线程安全（不安全）
* */
public class Singleton3 {

    private static Singleton3 instance;

    //静态内部类中持有单例对象实例。
    private static Singleton3 getInstance() {

        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null)
                    instance = new Singleton3();//线程不安全发生在这里:因为这句话可能会被JIT重排序。
            }
        }
        return instance;
    }
}
