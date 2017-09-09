package cn.edu.seu.itbook.algorithm4.sort;

import java.util.Scanner;

/**
 * 堆排序
 *
 * @Author personajian
 * @Date 2017/9/5 21:00
 */
public class HeapSort extends AbstractSortion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int[] original = {3, 1, 2, 8, 6, 9, 5, 4, 0, 7};
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = original[i];
        }
        in.close();
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

    public static void sort(Comparable[] a) {
        int len = a.length;

        /*buildHeap(a, len - 1);

        for (int i = len - 1; i > 0; i--) {
            exch(a, i, 0);
            adjustDown(a, 0, i - 1);
        }*/

        buildHeap(a, len - 1);
        //buildHeap0(a, len - 1);

        for (int i = len - 1; i >=0; i--) {
            exch(a, i, 0);
            adjustDown(a, 0, i - 1);
        }

    }

    /**Floyd建堆：自下而上，下滤
     * 迭代调用堆调整来建立堆
     * @Param
     * @Return
     */
    private static void buildHeap(Comparable[] a, int len) {
        //i=n/2~1，反复调整堆：从最底层的非叶子结点开始调整，典型的Floyd建堆
        for (int index = len / 2; index >= 0; index--) {
            adjustDown(a, index, len);
        }
    }
    /**暴力建堆：自上而下，上滤
     * 迭代调用堆调整来建立堆
     * @Param
     * @Return
     */
    private static void buildHeap0(Comparable[] a, int len) {
        //i=n/2~1，反复调整堆：从最底层的非叶子结点开始调整，典型的Floyd建堆

        for (int i = 1; i <=len; i++) {
            adjustUp(a,0,i);
        }

    }


    //delMax()相同功能，将首节点下滤
    private static void adjustDown(Comparable[] a, int index, int len) {
        Comparable temp = a[index];//temp暂存要调整的值

        //注意推排序利用的是完全二叉树的性质，子结点的下标=2*父结点的下标(+1)

        int i=son(index);



        while(i<=len){
            if(i+1<=len){
                if(less(a[i],a[i+1]))
                    i++;
            }

            if(less(temp,a[i])){
                a[index]=a[i];
                index=i;
                i=son(i);
            }else
                break;

        }

        a[index]=temp;


        /*for (int i = 2 * index+1; i <= len; i *= 2) {
            if(i+1<=len){
                if (less(a[i], a[i + 1]))//沿key较大的子结点向下筛选
                    i++;
            }


            if (!less(temp, a[i]))//若需要调整结点大于所有子结点，就跳出。
                break;
            else {
                a[index] = a[i];//将a[i]调整到双亲上
                index = i;//修改index的值继续向下筛选
            }
        }

        a[index] = temp;//被筛选结点的值放入最终位置*/
    }

    //insert()相同功能：将末结点上滤
    private static void adjustUp(Comparable[] a,int index,int len){
        Comparable temp=a[len];

        int i=parent(len);

        while(i>=index){
            if(less(a[i],temp)){
                a[len]=a[i];
                len=i;
                i=parent(i);
            }else
                break;
        }

        a[len]=temp;

    }

    private static int parent(int index){
        if(index==0)
            return -1;
        int mod=index%2;
        if(mod==1)
            return index/2;
        else
            return (index-1)/2;
    }

    private static int son(int index){
        return 2*index+1;
    }
}
