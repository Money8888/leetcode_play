package com.leetcode.learn.tree;

/**
 * 二叉树的最大路径和，即根节点到叶子节点的最长路径
 *   1
 *     2
 *    3  4
 *      5
 *
 */
public class OneSideMax {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.right = node1;
        root.left = null;
        node1.left = node2;
        node1.right = node3;
        node2.left = null;
        node2.right = null;
        node3.left = node4;
        node3.right = null;
        node4.right = null;
        node4.left = null;

        int sum = oneSideMax(root);
        System.out.println(sum);
    }

    // 保存所有节点值的和
    private static int ans = Integer.MIN_VALUE;

    private static int oneSideMax(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = Math.max(0, oneSideMax(root.left));
        int right = Math.max(0, oneSideMax(root.right));
        ans = Math.max(ans, left + right + root.val);
        // return ans;
        return Math.max(left, right) + root.val;
    }
}
