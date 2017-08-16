package cn.edu.seu.demo.annotation;

import java.lang.reflect.Field;

/**
 * Created by personajian on 2017/7/19.
 */
public class StudentInfoUtil {

    public static void getStudentInfo(Class<?> clazz) {
        // 获取程序对象所有的Field注释
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 判断是否为Student注释
            if (field.isAnnotationPresent(Student.class)) {
                // 获取Annotation对象
                Student student = field.getAnnotation(Student.class);
                System.out.println("姓名" + student.name() + "年龄" + student.age()
                        + "性别" + student.sex());
            }
        }
    }

}


