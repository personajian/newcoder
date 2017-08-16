package cn.edu.seu.demo.thread;

import sun.misc.Unsafe;

/**利用Unsafe来实现同步锁synchronized
 * @Author personajian
 * @Date 2017/7/31 22:10
 */
public class CustomSynchronized {

    private Unsafe unsafe;

    public CustomSynchronized() {
        this.unsafe = new CustomUnsafe().getUnsafe();
    }

    public void lock(Object obj) {
        unsafe.monitorEnter(obj);
    }

    public void unlock(Object obj) {
        unsafe.monitorExit(obj);
    }

}
