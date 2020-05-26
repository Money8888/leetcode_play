package com.leetcode.learn.dynamic_program;

/**
 * 字符串的模式匹配问题
 */
public class KMP {
    public static void main(String[] args) {
        String patten = "aaab";
        String text = "aaacaaaaaabaa";
        KmpMain km = new KmpMain(patten);
        int i = km.search(text);
        System.out.println(i);
    }


    private static class KmpMain{
        // dp数组
        // dp[j]['A'] = next 表⽰，当前是状态 j ，遇到了字符'A' ，应该转移到状态next
        private int[][] dp;
        // kmp算法的dp数组只和模式串有关
        private String pat;

        public KmpMain(String pat) {
            this.pat = pat;
            int M = pat.length();
            // 256表示所有ASCII码个数
            dp = new int[M][256];
            // 初始条件
            dp[0][pat.charAt(0)] = 1;
            // 待回退的状态X
            int X = 0;
            for(int j = 1; j < M; j++){
                for (int c = 0; c < 256; c++){
                    // 绑定回退的状态
                    dp[j][c] = dp[X][c];
                }

                dp[j][pat.charAt(j)] = j + 1;
                // 更新回退的状态
                X = dp[X][pat.charAt(j)];
            }
        }

        private int search(String text){
            int M = pat.length();
            int N = text.length();

            // pat的初始状态为0
            int j = 0;
            for (int i = 0; i < N; i++){
                // 计算pat的下一个状态
                j = dp[j][text.charAt(i)];
                // 如果j到达终态则返回
                if(j == M){
                    return i - M + 1;
                }
            }
            // 没有找到
            return -1;
        }
    }
}
