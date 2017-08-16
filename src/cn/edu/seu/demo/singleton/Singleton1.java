package cn.edu.seu.demo.singleton;
/*
* 单例模式（懒汉式），但是线程不安全
* */
public class Singleton1 {
    //单例对象未实例化，调用getInstance()时实例化
    private static Singleton1 instance;

    private Singleton1(){

    }

    public static Singleton1 getInstance() {
        if(instance==null){
            instance=new Singleton1();
        }
        return instance;
    }
}
