package com.leetcode.learn.tree.AVL;

/**
 * 平衡二叉树测试
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        Integer[] es = new Integer[]{3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
        //批量插入
        avlTree.insert(es);
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //数量
        System.out.println(avlTree.size());


        System.out.println("======>首先删除5  此时没有影响,不需要重平衡");
        avlTree.delete(5);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());


        System.out.println("======>再次删除6  此时节点4的BF为2 需要右旋重平衡");
        avlTree.delete(6);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());

        System.out.println("======>再次删除11  由于该节点拥有左右子树,实际上删除的是该节点的右子树的最小节点12,然后将12的值赋值给11的节点,并导致节点12的原父节点11不平衡,需要重平衡");
        avlTree.delete(11);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());

        System.out.println("======>再次删除7  由于该节点拥有左右子树,实际上删除的是该节点的右子树的最小节点8,然后将8的值赋值给7的节点,并导致节点8的原父节点9不平衡,需要重平衡");
        avlTree.delete(7);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());

        System.out.println("======>再次删除9、12  此时不需要重平衡");
        avlTree.delete(9);
        avlTree.delete(12);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.toInorderTraversalString());

        System.out.println("======>最后删除8  由于该节点拥有左右子树,实际上删除的是该节点的右子树的最小节点10节点,然后将10的值赋值给8的节点,并导致节点10的原父节点13不平衡,需要重平衡");
        avlTree.delete(8);
        //检查是否平衡
        System.out.println(avlTree.checkBalance());
        //中序遍历输出
        System.out.println(avlTree.size());
        System.out.println(avlTree.toInorderTraversalString());

    }

}
