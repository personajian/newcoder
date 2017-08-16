package cn.edu.seu.demo.annotation;

/**
 * Created by personajian on 2017/7/19.
 */
public class Test {
    @Student(name = "Snail", age = 18, sex = Student.Sex.MAN)
    private StudentBean student01;
    @Student(name = "Snail2", age = 19, sex = Student.Sex.MAN)
    private StudentBean student02;

    public static void main(String[] args) {
        StudentInfoUtil.getStudentInfo(Test.class);
    }

}
