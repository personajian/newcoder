package cn.edu.seu.demo.singleton;

/*
* 双重检查锁+volatile实现懒汉单例的线程安全
* */
public class Singleton3_ {

    //private static Singleton3_ instance;
    //添加volatile
    private static volatile Singleton3_ instance;

    //静态内部类中持有单例对象实例。
    private static Singleton3_ getInstance() {

        if (instance == null) {
            synchronized (Singleton3_.class) {
                if (instance == null) {
                    //线程不安全发生在这里:因为这句话可能会被JIT重排序。
                    //instance被volatile修饰后，JIT不会对此段代码进行重排序了！！
                    instance = new Singleton3_();
                }
            }
        }
        return instance;
    }
}
