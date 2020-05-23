package com.leetcode.learn.tree;

/**
 * 判断是否是有效的二叉搜索树
 *
 */
public class IsValidBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        root.right = node1;
        root.left = null;
        node1.left = node2;
        node1.right = node3;
        node2.left = null;
        node2.right = null;
        node3.left = null;
        node3.right = null;
        boolean bst = isValidBST(root);
        System.out.println(bst);
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidRecurrence(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidRecurrence(TreeNode node, long min, long max){
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isValidRecurrence(node.left, min, node.val) && isValidRecurrence(node.right, node.val, max);
    }
}
