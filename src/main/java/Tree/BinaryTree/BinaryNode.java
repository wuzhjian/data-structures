package Tree.BinaryTree;

import java.io.Serializable;

/**
 * Created by Admin on 2018/11/27.
 */
public class BinaryNode<T extends Comparable> implements Serializable{
    public T data;

    public BinaryNode<T> left;

    public BinaryNode<T> right;

    public BinaryNode( T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.left = left;
        this.data = data;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data, null, null);
    }

    /**
     * 是否为叶子节点
     * @return
     */
    public boolean isLeaf() {
        return this.left == null && this.right==null;
    }
}


