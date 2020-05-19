package com.leetcode.learn.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 先序遍历的递归算法和非递归算法
 */
public class PreorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.right = node1;
        root.left = null;
        node1.left = node2;
        node1.right = null;
        node2.left = null;
        node2.right = null;
        List<Integer> integerList = preorderTraversal(root);
        System.out.println(integerList);
    }

    private static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //preorder(root, result);
        preorder2(root, result);
        return result;
    }

    // 递归算法
    private static void preorder(TreeNode root, List<Integer> result) {
        if(root == null){
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    // 非递归算法
    private static void preorder2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;

        while (curNode != null || !stack.isEmpty()){
            while (curNode != null){
                // 把根节点压栈
                stack.push(curNode);
                result.add(curNode.val);
                curNode = curNode.left;
            }
            // 此时左孩子为空，则将当前节点置为栈顶节点的右孩子
            curNode = stack.pop().right;
        }
    }
}
