package cn.edu.seu.demo.newcoder;

/**null 42 42
 * @Author personajian
 * @Date 2017/9/3 18:49
 */

class Two {
    Byte x;//包装类型
}

class PassO {
    public static void main(String[] args) {
        PassO p = new PassO();
        p.start();
    }

    void start() {
        Two t = new Two();
        System.out.print(t.x + " ");
        Two t2 = fix(t);
        System.out.print(t.x + " " + t2.x);
    }

    Two fix(Two tt) {
        tt.x = 42;
        return tt;
    }
}
