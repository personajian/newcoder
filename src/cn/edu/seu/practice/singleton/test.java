package cn.edu.seu.practice.singleton;

public class test {

    public static void main(String[] args) {
        //饿汉式单例
        Singleton0 singleton0=Singleton0.getInstance();
        Singleton0 singleton00=Singleton0.getInstance();
        System.out.println(singleton0);
        System.out.println(singleton00);
        System.out.println(singleton0.equals(singleton00));

        //懒汉式单例
        Singleton1 singleton1=Singleton1.getInstance();
        Singleton1 singleton11=Singleton1.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton11);
        System.out.println(singleton1.equals(singleton11));

        //静态内部类方式实现懒汉式单例的线程安全
        Singleton2 singleton2=Singleton2.getInstance();
        Singleton2 singleton21=Singleton2.getInstance();
        System.out.println(singleton2);
        System.out.println(singleton21);
        System.out.println(singleton2.equals(singleton21));
    }
}
