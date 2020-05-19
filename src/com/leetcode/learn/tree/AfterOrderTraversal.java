package com.leetcode.learn.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历的递归算法和非递归算法
 */
public class AfterOrderTraversal {
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
        List<Integer> integerList = afterOrderTraversal(root);
        System.out.println(integerList);
    }

    private static List<Integer> afterOrderTraversal(TreeNode root) {
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
        preorder(root.left, result);
        preorder(root.right, result);
        result.add(root.val);
    }

    // 非递归算法
    private static void preorder2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        //用来记录最新出栈的节点
        TreeNode flagNode = null;

        // 所有左节点入栈
        while (curNode != null){
            stack.push(curNode);
            curNode = curNode.left;
        }

        while (!stack.isEmpty()){
            curNode = stack.pop();

            //如果当前节点的右儿子与flag相同，说明当前节点右子树已完成遍历
            if(curNode.right == null || curNode.right == flagNode){
                result.add(curNode.val);
                flagNode = curNode;
            }else {
                stack.push(curNode);
                curNode = curNode.right;
                while (curNode != null){
                    stack.push(curNode);
                    curNode = curNode.left;
                }
            }
        }
    }
}
