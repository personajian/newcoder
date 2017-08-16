package cn.edu.seu.demo.thread;

/**
 * @Author personajian
 * @Date 2017/7/31 22:22
 */
public class CustomSynchronizedTest {
    public static void main(String[] args) {
        TestTask task = new TestTask();
        new Thread(() -> task.method1()).start();
        new Thread(() -> task.method2()).start();

        /**
         输出：
         method1() execute!
         method2() execute!

         不使用锁保持同步：
         method2() execute!
         method1() execute!
         */
    }

    static class TestTask {
        private CustomSynchronized synchronzied = new CustomSynchronized();

        public void method1() {
            synchronzied.lock(this);
            try {
                //模拟方法需要执行100毫秒
                Thread.sleep(100);
                System.out.println("method1() execute!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                synchronzied.unlock(this);
            }

            /*//不适用同步，模拟方法需要执行100毫秒
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method1() execute!");*/
        }

        public void method2() {
            synchronzied.lock(this);
            System.out.println("method2() execute!");
            synchronzied.unlock(this);
        }
    }

}
