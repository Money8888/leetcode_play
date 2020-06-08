package com.leetcode.learn.tree.red_black;

/**
 * 参考大佬的
 * https://blog.csdn.net/weixin_43767015/article/details/106311869
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> integerIntegerRedBlackTree = new RedBlackTree<>();
        integerIntegerRedBlackTree.put(3, 47);
        integerIntegerRedBlackTree.put(2, 16);
        integerIntegerRedBlackTree.put(1, 73);
        integerIntegerRedBlackTree.put(4, 1);
        integerIntegerRedBlackTree.put(5, 24);
        integerIntegerRedBlackTree.put(6, 59);
        integerIntegerRedBlackTree.put(7, 59);
        integerIntegerRedBlackTree.put(16, 59);
        integerIntegerRedBlackTree.put(15, 59);
        integerIntegerRedBlackTree.put(14, 11);
        integerIntegerRedBlackTree.put(13, 59);
        integerIntegerRedBlackTree.put(12, 59);
        integerIntegerRedBlackTree.put(11, 12);
        integerIntegerRedBlackTree.put(10, 59);
        integerIntegerRedBlackTree.put(8, 59);
        integerIntegerRedBlackTree.put(9, 59);
        /*中序遍历输出,即根据排序顺序从小到大输出*/
        System.out.println(integerIntegerRedBlackTree.toInorderTraversalString());

        System.out.println(integerIntegerRedBlackTree.remove(4));
        System.out.println(integerIntegerRedBlackTree.remove(5));
        System.out.println(integerIntegerRedBlackTree.remove(12));
        System.out.println(integerIntegerRedBlackTree.remove(7));
        System.out.println(integerIntegerRedBlackTree.remove(13));
        System.out.println(integerIntegerRedBlackTree.remove(8));
        System.out.println(integerIntegerRedBlackTree.remove(16));
        System.out.println(integerIntegerRedBlackTree.remove(11));
        System.out.println(integerIntegerRedBlackTree.remove(10));
        System.out.println(integerIntegerRedBlackTree.remove(14));
        System.out.println(integerIntegerRedBlackTree.remove(3));
        System.out.println(integerIntegerRedBlackTree.remove(9));
        System.out.println(integerIntegerRedBlackTree.remove(6));
        System.out.println(integerIntegerRedBlackTree.remove(15));
        System.out.println(integerIntegerRedBlackTree.remove(1));

        System.out.println(integerIntegerRedBlackTree.toInorderTraversalString());

    }
}
