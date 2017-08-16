package cn.edu.seu.demo.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

/**
 * @Author personajian
 * @Date 2017/7/31 21:50
 */
public class CustomUnsafe {

    private Unsafe unsafe;

    public Unsafe getUnsafe() {
        return unsafe;
    }

    public CustomUnsafe(){
        try {
            //获得Unsafe的构造器
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            //突破私有访问权限
            constructor.setAccessible(true);
            //创建Unsafe实例
            this.unsafe = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
