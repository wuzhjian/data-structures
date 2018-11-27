package Tree.BinaryTree;

import jdk.nashorn.internal.ir.BinaryNode;

/**
 * Created by Admin on 2018/11/27.
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {
    protected Tree.BinaryTree.BinaryNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }


    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public String preOrder() {
        return null;
    }

    public String inOrder() {
        return null;
    }

    public String postOrder() {
        return null;
    }

    public String levelOrder() {
        return null;
    }

    public void insert() {

    }

    public void remove() {

    }

    public T findMax() {
        return null;
    }

    public T findMin() {
        return null;
    }

    public BinaryNode findNode(T data) {
        return null;
    }

    public boolean contains(T data) throws Exception {
        return false;
    }

    public void clear() {

    }
}
