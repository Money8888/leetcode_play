package com.leetcode.learn.opt;

/**
 * 经典！
 * 建议多看！
 * 博弈问题
 * 扔石头问题的博弈
 * 你和你的朋友⾯前有⼀排⽯头堆，⽤⼀个数组 piles 表⽰，piles[i] 表⽰第 i
 * 堆⽯⼦有多少个。你们轮流拿⽯头，（⼀次拿⼀堆），但是只能拿⾛最左边或者
 * 最右边的⽯头堆。所有⽯头被拿完后，谁拥有的⽯头多，谁获胜
 * 返回最后的石头数之差
 */
public class ThrowStone {

    public static void main(String[] args) {
        int[] piles = {1,4,100,2,89};
        int diff = throwStone(piles);
        System.out.println(diff);
    }

    /**
     * 扔石头博弈
     * @param piles
     * @return
     */
    private static int throwStone(int[] piles) {
        int n = piles.length;
        // 构建dp数组
        // dp[i][j].first的含义是从第i到j的石头堆里面的先手能获得的最多的石头数
        Pair[][] dp = new Pair[n][n];
        // 初始化
        for(int i = 0; i < n; i++){
            // 因为i一定小于等于j，只取对角线上半部分
            for(int j = i; j < n; j++){
                dp[i][j] = new Pair(0, 0);
            }
        }
        // 边界问题，当只有一堆石头即i==j时的情况
        // 此时先手是一堆石头的总数，后手没有石头
        for(int i = 0; i < n; i++){
            dp[i][i].first = piles[i];
            dp[i][i].second = 0;
        }
        // 因为对角线是斜着的，并且dp[i][j]之和dp[i+1][j]和dp[i][j-1]有关
        // 所以采取斜着遍历数组
        for(int l = 2; l <= n; l++){
            for(int i = 0; i <= n - l; i++){
                int j = i + l - 1;
                // (i,j)为(0,1),(1,2),(2,3),(0,2),(1,3).....

                // 当先手取左边的堆时，dp相当于向右走一格,且拿完之后变成后手
                int left = piles[i] + dp[i + 1][j].second;
                // 若先手取右边的堆时，dp相当于向左走一格,也是变为后手
                int right = piles[j] + dp[i][j - 1].second;
                if(left > right){
                    // 说明拿左边更好石头更多
                    dp[i][j].first = left;
                    // 则后手为拿了一个后的先手
                    dp[i][j].second = dp[i + 1][j].first;
                }else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j - 1].first;
                }
            }
        }
        Pair res = dp[0][n - 1];
        return res.first -res.second;

    }

    private static class Pair{
        // 先手的最多石头数
        int first;
        // 后手的最多石头数
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}
