package com.leetcode.learn.dynamic_program;

/**
 * 给定矩形长宽，求有多少种走法
 *
 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 * 组合数Cm+n−2,m−1​

 */
public class UniquePaths {

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int paths = uniquePaths(m, n);
        System.out.println(paths);
    }


    public static int uniquePaths(int m, int n) {
        // 令 dp[i][j] 是到达 i, j 最多路径
        // 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 边界条件: 对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
