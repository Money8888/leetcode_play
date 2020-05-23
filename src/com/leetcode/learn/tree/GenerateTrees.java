package com.leetcode.learn.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入参数n
 * 求节点为1到n所有可能的二叉搜索树
 * 采用递归
 * 递归创建左子树和右子树
 */
public class GenerateTrees {

    public static void main(String[] args) {
        int n = 3;
        List<TreeNode> trees = generateTrees(3);
        System.out.println(trees);
    }

    private static List<TreeNode> generateTrees(int n){
        if(n == 0){
            return new LinkedList<>();
        }
        return generateRecurrence(1, n);
    }

    // 递归创建
    private static List<TreeNode> generateRecurrence(int start, int end){

        List<TreeNode> trees = new LinkedList<>();

        if(start > end){
            trees.add(null);
            return trees;
        }

        for(int i = start; i <= end; i++){
            // 递归创建左子树
            List<TreeNode> leftTree = generateRecurrence(start, i - 1);
            // 递归创建右子树
            List<TreeNode> rightTree = generateRecurrence(i + 1, end);

            // 将根节点插入到数组中
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = left;
                    currentNode.right = right;
                    trees.add(currentNode);
                }
            }
        }

        return trees;
    }
}
