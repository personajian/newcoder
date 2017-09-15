package cn.edu.seu.demo.reference;

/**
 * @Author personajian
 * @Date 2017/9/14 10:37
 */
public class ByReference {

    public static void main(String[] args) {
        System.out.println("————引用类型————");
        Node node=new Node();
        node.value=0;
        System.out.println("调用方法前："+node.value);
        change(node);
        System.out.println("调用方法后："+node.value);
        System.out.println("————基本类型————");
        int a=0;
        System.out.println("调用方法前："+a);
        change(a);
        System.out.println("调用方法后："+a);

    }

    private static void change(int a) {
        a=1;
    }

    private static void change(Node node) {
        node.value=1;
    }

    private static class Node{
        int value;
    }


}
