package cn.edu.seu.practice.singleton;
/*
* 单例模式（饿汉式）
* */
public class Singleton0 {
    //单例对象已实例化。
    private static Singleton0 instance=new Singleton0();

    private Singleton0(){

    }

    public static  Singleton0 getInstance() {
        return instance;
    }
}
