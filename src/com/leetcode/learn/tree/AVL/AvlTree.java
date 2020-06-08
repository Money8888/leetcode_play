package com.leetcode.learn.tree.AVL;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 平衡二叉树的操作
 */
public class AvlTree<E> {
    // 树节点
    public static class BinaryTreeNode<E> {
        // 数据域
        E data;
        // 左右子节点
        BinaryTreeNode<E> left;
        BinaryTreeNode<E> right;
        // 节点高度 从0开始,从下往上;null节点高度返回-1
        int height;

        public BinaryTreeNode(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // 根节点的引用
    private BinaryTreeNode<E> root;

    // 自定义比较器
    private Comparator<? super E> cmp;

    // 节点个数
    private int size;

    public AvlTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    public AvlTree() {
    }

    // 是否是空树
    public boolean isEmpty(){
        return size == 0;
    }

    // 返回节点数
    public int size(){
        return size;
    }

    // 比较两个节点大小
    private int compare(E e1, E e2){
       if(cmp != null){
           return cmp.compare(e1, e2);
       }else {
           return ((Comparable<E>) e1).compareTo(e2);
       }
    }

    // 输出遍历打印
    List<BinaryTreeNode<E>> str = new ArrayList<>();

    public String toInorderTraversalString(){
        //如果是空树,直接返回空
        if (isEmpty()) {
            return null;
        }
        // 中序遍历
        inorderTraversal(root);
        String s = str.toString();
        str.clear();
        return s;
    }

    // 中序遍历内部实现
    private void inorderTraversal(BinaryTreeNode<E> root){
        // 获取左子节点
        BinaryTreeNode<E> left = getLeft(root);
        if(left != null){
            inorderTraversal(left);
        }
        // 添加数据节点
        str.add(root);
        // 获取右子节点
        BinaryTreeNode<E> right = getRight(root);
        if(right != null){
            inorderTraversal(right);
        }
    }

    // 获取左子节点
    private BinaryTreeNode<E> getLeft(BinaryTreeNode<E> root) {
        return root == null ? root : root.left;
    }

    // 获取右子节点
    private BinaryTreeNode<E> getRight(BinaryTreeNode<E> root) {
        return root == null ? root : root.right;
    }

    // 获取根节点
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    // 获取节点的height
    private int getHeight(BinaryTreeNode<E> node){
        return node == null ? -1 : node.height;
    }

    // 查找节点，查到返回true，没找到返回false
    public boolean contains(E e){
        return contains(e, root);
    }

    private boolean contains(E e, BinaryTreeNode<E> root){
        if(root == null){
            return false;
        }

        int i = compare(e, root.data);
        if(i > 0){
            return contains(e, root.right);
        }else if(i < 0){
            return contains(e, root.left);
        }else {
            return true;
        }
    }

    // 判断是否平衡
    private boolean balance = true;
    public boolean checkBalance(){
        checkBalance(root);
        boolean balanceNow=balance;
        // 回写全局变量
        balance=true;
        return balanceNow;
    }

    // 递归检查是否平衡,实际上这里采用了后序遍历,即左子节点-右子节点-根节点的方法递归遍历
    private int checkBalance(BinaryTreeNode<E> root){
        if(root == null){
            return -1;
        }
        // 返回左子树的高度
        int hl = checkBalance(root.left);
        // 返回右子树的高度
        int hr = checkBalance(root.right);

        if((Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) || getHeight(root.left) != hl || getHeight(root.right) != hr){
            balance = false;
        }
        return getHeight(root);
    }

    // 插入元素
    public void insert(E e){
        root = insert(e, root);
    }

    // 要插入的元素的数组,注意,数组元素的顺序存储的位置将会影响二叉排序树的生成
    public void insert(E[] es){
        for (E e : es) {
            root = insert(e, root);
        }
    }

    // 插入数据
    private BinaryTreeNode<E> insert(E e, BinaryTreeNode<E> root){
        // 空树的情况
        if (root == null) {
            size++;
            return new BinaryTreeNode<>(e);
        }

        // 调用比较
        int i = compare(e, root.data);
        if(i > 0){
            // 说明该插入的数据比根节点大，更新根节点的右子树
            root.right = insert(e, root.right);
        }else if(i < 0){
            // 说明该插入的数据比根节点小，更新根节点的左子树
            root.left = insert(e, root.left);
        }else {
            // 什么也不需要干
        }
        // 重新平衡该二叉树
        return rebalance(root);
    }

    // 重平衡二叉树
    // * 1)	在节点X的左孩子节点的左子树中插入元素，简称LL 右旋
    // * 2)	在节点X的左孩子节点的右子树中插入元素，简称LR 左-右双旋
    // * 3)	在节点X的右孩子节点的左子树中插入元素，简称RL 左旋
    // * 4)	在节点X的右孩子节点的右子树中插入元素，简称RR 右-左双旋
    private BinaryTreeNode<E> rebalance(BinaryTreeNode<E> root) {
        if(root == null){
            return null;
        }
        // 开始旋转
        // 1,2种情况
        if(getHeight(root.left) - getHeight(root.right) > 1){
            if(getHeight(root.left.left) >= getHeight(root.left.right)){
                // 右旋
                root = rotateRight(root);
            }else {
                // 左右旋
                root = rotateLeftAndRight(root);
            }
        }else if(getHeight(root.right) - getHeight(root.left) > 1){
            if(getHeight(root.right.right) >= getHeight(root.right.left)){
                // 左旋
                root = rotateLeft(root);
            }else {
                // 右左旋
                root = rotateRightAndLeft(root);
            }
        }

        // 重新计算高度
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }

    // 右旋
    private BinaryTreeNode<E> rotateRight(BinaryTreeNode<E> k1) {
        // 获取k1的左子节点
        BinaryTreeNode<E> k2 = k1.left;
        // k2的右子树成为k1的左子树
        k1.left = k2.right;
        // k1成为k2的右子节点
        k2.right = k1;
        // k1的高度等于k1的左或者右子树的高度的最大值+1
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        // k2的高度等于k2的左子节点和k1的高度(此时k1就是k2的右子节点)的最大值+1
        k2.height = Math.max(getHeight(k2.left), k1.height) + 1;
        // 此时k2成为子树的根节点
        return k2;
    }

    // 左旋
    private BinaryTreeNode<E> rotateLeft(BinaryTreeNode<E> k1){
        // 获取k1的右子节点
        BinaryTreeNode<E> k2 = k1.right;
        // k2的左子树成为k1的右子树
        k1.right = k2.left;
        // k1成为k2左节点
        k2.left = k1;
        //k1的高度等于k1的左或者右子树的高度的最大值+1;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        //k2的高度等于k2的右子节点和k1的高度(此时k1就是k2的左子节点)的最大值+1
        k2.height = Math.max(getHeight(k2.right), k1.height) + 1;
        //返回k2,k2成为根节点
        return k2;
    }

    // 右-左双旋
    private BinaryTreeNode<E> rotateRightAndLeft(BinaryTreeNode<E> k1){
        // 先对k1的右子节点k2进行右旋,返回右旋之后的根节点k3,然后使得成为k3成为的k1的左子树
        k1.right = rotateRight(k1.right);
        // 然后对k1进行左旋,成为k3的左子树,返回的根节点就是k3,即返回旋转之后的根节点
        return rotateLeft(k1);
    }

    // 左-右双旋
    private BinaryTreeNode<E> rotateLeftAndRight(BinaryTreeNode<E> k1){
        // 先对k1的左子节点k2进行左旋,返回左旋之后的根节点k3,然后使得成为k3成为的k1的左子树
        k1.left = rotateLeft(k1.left);
        // 然后对k1进行右旋,成为k3的右子树,返回的根节点就是k3,即返回旋转之后的根节点
        return rotateRight(k1);
    }

    // 查找最小值，最左的节点一定是最小
    private BinaryTreeNode<E> findMin(BinaryTreeNode<E> root){
        if(root == null){
            return null;
        }else if(root.left == null){
            return root;
        }
        return findMin(root.left);
    }

    // 查找最大值，最右的节点一定是最大
    private BinaryTreeNode<E> findMax(BinaryTreeNode<E> root){
        if(root == null){
            return null;
        }else if(root.right == null){
            return root;
        }
        return findMin(root.right);
    }

    // 删除
    public void delete(E e) {
        //返回root,但此时可能有一个节点已经被删除了
        root = delete(e, root);
    }

    private BinaryTreeNode<E> delete(E e, BinaryTreeNode<E> root){
        if(root == null){
            return null;
        }

        int i = compare(e, root.data);
        if(i > 0){
            // 待删除的元素比根节点大
            root.right = delete(e, root.right);
        }else if(i < 0){
            root.left = delete(e, root.left);
        }else {
            // 删除节点
            if(root.left != null && root.right != null){
                // 如果两个子节点都不为null
                // 让右子树最小值去代替当前的根节点值
                root.data = findMin(root.right).data;
                // 然后更新右子树
                root.right = delete(root.data, root.right);
            }else {
                // 有一个节点为空,则直接把此时的root直接赋给root
                root = (root.left != null) ? root.left : root.right;
                --size;
            }
        }

        // 重新平衡该树
        return rebalance(root);
    }



}
