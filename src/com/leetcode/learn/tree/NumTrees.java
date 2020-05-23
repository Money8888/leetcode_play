package com.leetcode.learn.tree;
/**
 * 输入参数n
 * 求节点为1到n的有多少种可能的二叉搜索树
 *
 * 可以遍历每个数字 i，
 * 将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。
 * 于是可以递归地从子序列构建子树
 * 由于根的不同，所以二叉树一定是唯一的
 * 设dp[n] 为n个元素组成的序列所构成的二叉搜索时的个数
 * f(i,n) 为n个元素的序列以第i个元素为根的二叉搜索树的个数
 * 则f(i,n) = (左子树个数)dp(i - 1) * dp(n - i)(右子树个数)
 * 而dp(n) = sum(f(i,n)) i从1到n
 * 所以dp(n) = sum(dp(i - 1) * dp(n - i))
 *
 * 事实上，数量是卡特兰数
 */
public class NumTrees {

    public static void main(String[] args) {
        int n = 3;
        int numTrees = numTrees(n);
        System.out.println(numTrees);
    }

    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
             for(int j = 1; j <= i; j++){
                 dp[i] += dp[j - 1] * dp[i - j];
             }
        }
        return dp[n];
    }
}
