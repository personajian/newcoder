package cn.edu.seu.itbook.algorithm4.search;
/*
* 二叉查找树
* */
public class BST<Key extends Comparable<Key>,Value> {
    //二叉查找树的根节点
    private Node root;
    //二叉查找树的节点（递归定义）
    private class Node{
        Key key;
        Value value;
        Node left,right;
        int N;
    }

    public Value get(Key key){
        return get(key,root);
    }
    /*
    * 二叉查找树的查找递归实现
    * */
    private Value get(Key key,Node root){

        Node p=root;
        int differ=key.compareTo(root.key);
        if(differ>0) return get(key,p.right);
        else if(differ<0) return get(key,p.left);
        else return p.value;
    }
}
