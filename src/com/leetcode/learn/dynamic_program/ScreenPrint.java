package com.leetcode.learn.dynamic_program;

/**
 * 最多可以打印多少个A
 * 操作有
 *  输入A
 *  全选
 *  复制
 *  粘贴
 * 四种操作
 */
public class ScreenPrint {

    public static void main(String[] args) {
        // 允许的操作次数
        int N = 9;
        System.out.println(screenPrint(N));
    }

    private static int screenPrint(int N) {
        // dp[i]表示i次操作后最多能显示多少A
        int[] dp = new int[N + 1];
        // 边界条件，没有操作时不能打印出A
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            // 按A键
            dp[i] = dp[i - 1] + 1;
            for(int j = 2; j < i; j++){
                // 最优的操作序列⼀定是 C-A C-C 接着若⼲ C-V
                // j为C-V的起点
                // 每回粘贴粘dp[j - 2] 个A
                // 总共可以粘(i - j + 1)次
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[N];
    }

}
