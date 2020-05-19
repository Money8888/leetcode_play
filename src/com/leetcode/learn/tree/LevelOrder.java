package com.leetcode.learn.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层次遍历
 *   1
 *     2
 *    3  4
 */
public class LevelOrder {
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
        List<Integer> integerList = levelOrder(root);
        List<List<Integer>> singleIntegerList = singleLevelOrder(root);
        System.out.println(integerList);
        System.out.println(singleIntegerList);
    }

    /**
     * 层次遍历
     * @param root
     * @return
     */
    private static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        // 链队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            // 出队
            // 不同的是当队列为空时，调用pop()会抛出异常，而poll()会返回null
            TreeNode curNode = queue.poll();
            result.add(curNode.val);
            if(curNode.left != null){
                queue.add(curNode.left);
            }
            if(curNode.right != null){
                queue.add(curNode.right);
            }
        }
        return result;
    }

    private static List<List<Integer>> singleLevelOrder(TreeNode root) {
        // 每一层的元素
        List<Integer> layer = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null){
            return result;
        }

        queue.add(root);
        int start = 0, end = 1;
        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            layer.add(curNode.val);
            start ++;

            if(curNode.left != null){
                queue.add(curNode.left);
            }
            if(curNode.right != null){
                queue.add(curNode.right);
            }

            if(start == end){
                end = queue.size();
                start = 0;
                result.add(layer);
                layer = new ArrayList<>();
            }
        }
        return result;
    }

}
