package cn.edu.seu.demo.string;

/**String.intern()是一个Native方法，底层调用C++的 StringTable::intern 方法，
 * 源码注释：当调用 intern 方法时，如果常量池中已经该字符串，则返回池中的字符串；否则将此字符串添加到常量池中，并返回字符串的引用。
 * JDK1.6常量池在永久代中分配内存；永久代和Java堆的内存是物理隔离的
 * JDK1.7 之后，常量池在Java堆上分配内存。
 * 对于变量s1，常量池中没有 "StringTest" 字符串，s1.intern() 和 s1都是指向Java对象上的String对象。
 * 对于变量s2，常量池中一开始就已经存在 "java" 字符串，所以 s2.intern() 返回常量池中 "java" 字符串的引用。
 * @Author personajian
 * @Date 2017/8/14 21:34
 */
public class Stringintern {
    public static void main(String args[]) {
        String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }
}
