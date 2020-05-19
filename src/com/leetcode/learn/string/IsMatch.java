package com.leetcode.learn.string;

/**
 * 通配符字符串匹配 :*匹配全部，? 匹配单个字符
 * 拿模式串p去匹配整个文本串s
 * 动态规划
 */
public class IsMatch {

    public static void main(String[] args) {
        String s = "acdcb";
        String p = "a*d?b";
        boolean match = isMatch(s, p);
        System.out.println(match);
    }

    /**
     *状态矩阵 dp[i][j] 表示s的前i个字符和p的前j个字符是否匹配，值为bool类型
     * 状态转移:
     *      1、当s[i] == p[j] 或 p[j] == ? 则dp[i][j] = dp[i-1][j-1]
     *      2、当p[j] == * 则dp[i][j] = dp[i][j - 1](*匹配的是空字符) 或 dp[i][j] = dp[i-1][j](*匹配的是非空字符)
     * 初始化 dp[0][j] 表示s为空，只有p开始为* 才能为true，dp[i][0] 全为false, dp[0][0]为true
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 构建状态矩阵，默认为false
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化
        dp[0][0] = true;
        for(int i = 1; i <= n; i++){
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        // 状态转移
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m][n];
    }
}
