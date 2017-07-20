package cn.edu.seu.itbook.algorithm4.search;
/*
* 有序数组的二分查找
* */
public class BinarySearchST<Key extends Comparable<Key>,Value> {

    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity){
        keys=(Key[]) new Comparable[capacity];//泛型数据定义
        values=(Value[]) new Object[capacity];
        N=capacity;
    }


    public int rank(Key key){
        return rank(key,0,N-1);
    }
    /*
    * 有序数组的二分查找（递归实现）
    * */
    private int rank(Key key, int low, int high) {
        int middle=low+(high-low)/2;
        int differ=key.compareTo(keys[middle]);
        if(differ>0) return rank(key,low,middle-1);
        else if(differ<0) return rank(key,middle+1,high);
        else return middle;
    }
    /*
    * 有序数组的二分查找（迭代实现）
    * */
    private int rank1(Key key, int low, int high) {
        int middle,differ;
        while(low<=high){
            middle=low+(high-low)/2;
            differ=key.compareTo(keys[middle]);
            if(differ>0) high=middle-1;
            else if(differ<0) low=middle+1;
            else return middle;
        }
        return low;
    }
}
