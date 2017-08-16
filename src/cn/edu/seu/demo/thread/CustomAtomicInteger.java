package cn.edu.seu.demo.thread;

import org.junit.Assert;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;

/**利用Unsafe实现原子变量类
 * @Author personajian
 * @Date 2017/7/31 21:51
 */
public class CustomAtomicInteger {
    private volatile int value;
    private Unsafe unsafe;
    private long offset;

    public CustomAtomicInteger() {
        //利用自定义的CustomUnsafe来获取Unsafe实例
        unsafe=new CustomUnsafe().getUnsafe();
        try {
            //获得value变量的内存偏移量即内存地址
            offset = unsafe.objectFieldOffset(CustomAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public int incrementAndGet() {
        while (true) {
            int expected = value;
            int next = expected + 1;
            if (unsafe.compareAndSwapInt(this, offset, expected, next)) {
                return next;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomAtomicInteger atomicInteger = new CustomAtomicInteger();
        int maxThread = 1000;
        CountDownLatch latch = new CountDownLatch(maxThread);
        for (int i = 0; i < maxThread; i++) {
            new Thread(() -> {
                System.out.println(atomicInteger.incrementAndGet());
                latch.countDown();
            }).start();
        }
        latch.await();//等待所有线程执行完毕
        Assert.assertEquals(atomicInteger.incrementAndGet(), maxThread + 1);
    }
}
