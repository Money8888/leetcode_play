package com.leetcode.learn.tree.BST;

import com.leetcode.learn.tree.TreeNode;

/**
 * 判断是否是有效的二叉搜索树
 *
 */
public class BST {
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

    // 是否是有效的二叉搜索树
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
        // 左子树的节点值范围为(min,node.val), 右子树的节点值范围为(node.val,max)
        // 如果当前节点会对下⾯的⼦节点有整体影响，可以通过辅助函数增⻓参数列表，借助参数传递信息
        return isValidRecurrence(node.left, min, node.val) && isValidRecurrence(node.right, node.val, max);
    }

    // 查找元素
    void BST(TreeNode root, int target) {
        if (root.val == target){
            return;
        }
            // 找到⽬标，做点什么
        if (root.val < target) {
            BST(root.right, target);
        }
        if (root.val > target) {
            BST(root.left, target);
        }
    }

    // BST插入节点,返回新BST的根节点
    TreeNode insertIntoBST(TreeNode root, int val){
        // 找到空位置插入节点
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val < val){
            // 此时相当于重组root的右子树并返回重组后的右子树的根节点
            root.right = insertIntoBST(root.right, val);
        }else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    // 删除节点,按值删除
    TreeNode deleteNode(TreeNode root, int val){
        if(root == null){
            return null;
        }

        if(root.val == val){
            // TODO: 删除逻辑 两种情况
            // 1、至少一个叶子节点为空
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }

            // 2、两个子节点都不为空时
            // 获取右子树中节点中最小的值作为此时的根节点
            TreeNode rightMinNode = getMinNode(root.right);
            // 也可以采取移位指针的方法让此时的root和rightMinNode有相同的左右孩子指向
            root.val = rightMinNode.val;
            // 删除右子树中最小节点后更新此时的右子树
            root.right = deleteNode(root.right, rightMinNode.val);

        }

        if(root.val < val){
            root.right = deleteNode(root.right, val);
        }else {
            root.left = deleteNode(root.left, val);
        }
        return root;
    }

    // 获取节点中最小的值，即最左的节点
    private TreeNode getMinNode(TreeNode node) {
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

}
