package com.leetcode.learn.dynamic_program;

/**
 * 两个字符串的最长公共子序列
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String str1 = "asffg";
        String str2 = "asmfg";
        int n = longestCommonSubsequence(str1, str2);
        System.out.println(n);
    }

    /**
     *
     * @return 返回最长公共子序列的长度
     */
    private static int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m + 1; i++){
            dp[i][0] = 0;
        }
        for(int j = 0; j < n + 1; j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m - 1][n - 1];

    }
}
