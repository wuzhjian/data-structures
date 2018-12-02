package Tree.BinaryTree;

/**
 * Created by Admin on 2018/11/27.
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {
    protected BinaryNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }


    public boolean isEmpty() {
        return false;
    }

    /**
     * 计算大小
     * @return
     */
    public int size() {
        return size(root);
    }


    private int size(BinaryNode<T> subtree) {
        if (subtree == null){
            return 0;
        } else {
            //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
            return size(subtree.left) + 1 + size(subtree.right);
        }
    }

    /**
     * 计算深度
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 计算深度，递归实现
     * @param subtree
     * @return
     */
    private int height(BinaryNode<T> subtree) {
        if (subtree == null){
            return 0;
        } else {
            int left = height(subtree.left);
            int right = height(subtree.right);
            return (left>right) ? (left+1):(right+1);// 返回并加上当前层
        }
    }


    public String preOrder() {
        String sb = preOrder(root);
        if (sb.length()>0){
            sb = sb.substring(0, sb.length()-1);
        }
        return sb;
    }


    /**
     * 先根遍历，每次总是先访问根结点，再访问左子树，最后访问右子树
     * @param subtree
     * @return
     */
    private String preOrder(BinaryNode<T> subtree) {
        StringBuffer sb = new StringBuffer();
        if (subtree!=null){
            // 先访问根节点
            sb.append(subtree.data+",");
            // 遍历左子树
            sb.append(preOrder(subtree.left));
            // 遍历右子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
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

    public void insert(T data) {
        if (data==null)
            throw new RuntimeException("data can\'Comparable be null !");
        // 插入操作
        root = insert(data, root);
    }

    /**
     * 插入操作，递归实现
     * @param data
     * @param p
     * @return
     */
    private BinaryNode<T> insert(T data, BinaryNode<T> p) {
        if (p==null){
            p = new BinaryNode<T>(data, null, null);
        }

        int compareResult = data.compareTo(p.data);

        if (compareResult <0){
            p.left = insert(data, p.left);
        } else if (compareResult >0){
            p.right = insert(data, p.right);
        }else {
            // 元素已经存在
        }
        return p;
    }



    public void remove(T data) {
        if (data == null)
            throw new RuntimeException("data can\'Comparable be null !");
        // 删除节点
        root = remove(data, root);
    }

    /**
     * 分3种情况
     * 1. 删除叶子节点
     * 2. 删除拥有一个孩子节点的节点(可能是左孩子也可能是右孩子)
     * 3. 删除拥有两个孩子节点的节点
     * @param data
     * @param p
     * @return
     */
    private BinaryNode<T> remove(T data, BinaryNode<T> p) {
        // 没有找到要删除的元素，递归结束
        if (p==null){
            return p;
        }

        int compareResult = data.compareTo(p.data);
        if (compareResult <0){
            p.left=remove(data, p.left);
        } else if (compareResult >0){
            p.right = remove(data, p.right);
        } else if (p.left!=null && p.right!=null){  // 已经找到节点并判断是否有两个子节点
            // 中级替换，找到右子树中最小元素并替换需要删除的元素值
            p.data = findMin(p.right).data;
            // 移除用于替换的节点
            p.right = remove(p.data,p.right);
        } else {
            // 拥有一个孩子节点的节点和叶子节点情况
            p = (p.left!=null)?p.left : p.right;
        }
        return p;  // 返回该节点
    }

    /**
     * 非递归删除
     * @param data
     * @return
     */
    public T removeUnrecure(T data) {
        if (data == null){
            throw new RuntimeException("data can\'Comparable be null !");
        }

        // 从根节点开始查找
        BinaryNode<T> current = this.root;
        // 记录父节点
        BinaryNode<T> parent = this.root;
        // 判断左右孩子的flag
        boolean isLeft = true;

        // 找到要删除的节点
        while (data.compareTo(current.data) != 0){
            // 更新父节点记录
            parent = current;
            int result = data.compareTo(current.data);

            if (result <0){ // 从左子树查找
                isLeft = true;
                current = current.left;
            } else if (result >0){ // 从右子树查找
                isLeft=false;
                current=current.right;
            }

            if (current==null){
                return null;
            }
        }

        // ----------到这里说明已找到要删除的结点

        // 删除的是叶子节点
        if (current.left==null && current.right==null){
            if (current == this.root){
                this.root = null;
            } else if (isLeft){
                parent.left = null;
            }else {
                parent.right = null;
            }
        }
        // 删除带有一个孩子结点的结点,当current的right不为null
        else if (current.left == null){
            if (current == this.root){
                this.root = current.right;
            } else if (isLeft){// current为parent的左孩子
                parent.left = current.right;
            }else {// current 为parent右孩子
                parent.right = current.right;
            }
        }
        // 删除带有一个 孩子的孩子节点，当current的left不为null
        else if (current.right == null){
            if (current == this.root){
                this.root = current.left;
            } else if (isLeft){  // current为parent的左孩子
                parent.left = current.left;
            } else {  // current为parent的右孩子
                parent.right = current.left;
            }
        }
        // 删除带有两个孩子节点的节点
        else {
            // 找到当前要删除节点current的右子树的最小值元素
            BinaryNode<T> successor = findSuccessor(current);

            if (current == root) {
                this.root = successor;
            } else if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            // 把当前要删除的节点左孩子赋值给successor
            successor.left = current.left;
        }
        return current.data;
    }

    private BinaryNode<T> findSuccessor(BinaryNode<T> delNode) {
        BinaryNode<T> successor = delNode;
        BinaryNode<T> successorParent = delNode;
        BinaryNode<T> current = delNode.right;

        // 不断查找左节点，直到为空，则successor为最小值
        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        // 如果要删除节点的有孩子与successor不相等，则执行如下操作(如果相等，则说明删除节点)
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            // 把中继节点的右孩子指向当前要删除节点的右孩子
            successor.right = delNode.right;
        }
        return successor;
    }

    public T findMax() throws EmptyTreeException {
        if (isEmpty())
            throw new EmptyTreeException("BinarySearchTree is empty!");
        return findMax(root).data;
    }

    /**
     * 查找最大值节点
     * @param p
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> p) {
        if (p == null)
            return null;
        else if (p.right == null)
            return p;
        return findMax(p.right);
    }

    public T findMin() throws EmptyTreeException {
        if (isEmpty())
            throw new EmptyTreeException("BinarySearchTree is empty!");
        return findMin(root).data;
    }


    /**
     * 查找最小值
     * @param p
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> p) {
        if (p == null)   // 结束条件
            return null;
        else if (p.left==null)
            return p;
        return findMin(p.left);
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
