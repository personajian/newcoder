package cn.edu.seu.practice.annotation;

/**
 * Created by personajian on 2017/7/19.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用于描述域
@Target(ElementType.FIELD)
// 运行是有效
@Retention(RetentionPolicy.RUNTIME)
public @interface Student {
    public enum Sex {
        MAN, WOMAN
    }

    /**
     * 1.参数成员只能用基本类型String,Enum,Class,annotations等数据类型,以及这一些类型的数组
     * 2.只能用public或默认(default)这两个访问权修饰. 3.如果只有一个参数成员,最好把参数名称设为"value"
     */
    String name() default "";

    // public修饰
    public int age() default 18;

    public Sex sex() default Sex.MAN;

}

