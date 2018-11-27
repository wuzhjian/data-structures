package Tree.BinaryTree;

import jdk.nashorn.internal.ir.BinaryNode;

/**
 * Created by Admin on 2018/11/27.
 */
public interface Tree<T extends Comparable> {

    boolean isEmpty();

    int size();

    int height();

    String preOrder();

    String inOrder();

    String postOrder();

    String levelOrder();

    void insert();

    void remove();

    T findMax();

    T findMin();

    BinaryNode findNode(T data);

    boolean contains(T data) throws Exception;

    void clear();
}
