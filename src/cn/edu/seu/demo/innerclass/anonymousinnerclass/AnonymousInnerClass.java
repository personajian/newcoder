package cn.edu.seu.demo.innerclass.anonymousinnerclass;

/**匿名内部类，结合抽象类和接口使用。典型使用场景：new Comparator(){};
 * @Author personajian
 * @Date 2017/8/31 11:21
 */

abstract class Contents{
    public abstract int value(int i);
}

public class AnonymousInnerClass {

    public Contents contents(){
        return new Contents(){
            private int i=1;
            public int value(int i){
                return i;
            }
        };
    }

    public static void main(String[] args) {
        AnonymousInnerClass anony=new AnonymousInnerClass();
        Contents c=anony.contents();
        System.out.println(c.value(5));
    }

}
