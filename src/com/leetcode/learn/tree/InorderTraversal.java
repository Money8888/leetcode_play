package com.leetcode.learn.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 * 递归算法和非递归算法（栈实现）
 */
public class InorderTraversal {

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
        List<Integer> integerList = inorderTraversal(root);
        System.out.println(integerList);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //inorder(root, result);
        inorder2(root, result);
        return result;
    }

    // 递归算法
    public static void inorder(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    // 非递归算法
    private static void inorder2(TreeNode root, List<Integer> result) {
        // 声明一个栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        TreeNode top;

        while(curNode != null || !stack.isEmpty()){
            // 一直将左孩子压入栈中
            while (curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }

            // 弹出栈
            top = stack.pop();
            result.add(top.val);
            curNode = top.right;
        }
    }
}
