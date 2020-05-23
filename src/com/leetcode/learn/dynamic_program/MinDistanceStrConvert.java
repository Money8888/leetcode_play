package com.leetcode.learn.dynamic_program;

/**
 * 字符串转换的最少操作
 * ⼀般来说，处理两个字符串的动态规划问题，都是按该思路处理
 *    插入一个字符
 *    删除一个字符
 *    替换一个字符
 *    解决两个字符串的动态规划问题，⼀般都是⽤两个指针 i,j 分别指向两个字符串的最后，
 *    然后⼀步步往前走，缩⼩问题的规模
 */
public class MinDistanceStrConvert {

    public static void main(String[] args) {
        String str1 = "intention";
        String str2 = "execution";
        int cnt = minDistanceStrConvert(str1, str2);
        int count = minDistanceStrConvertByOpt(str1, str2);
        System.out.println(cnt);
        System.out.println(count);
    }

    /**
     * 采用递归
     * 抛转引玉
     * 计算了多次dp[i][j]的重复子结构
     * 所以需要备忘录，采用动态规划
     * 见方法2
     */
    private static int minDistanceStrConvert(String str1, String str2){
        int m = str1.length(), n = str2.length();
        return dp(str1,str2,m - 1, n - 1);
    }

    /**
     * dp构造
     * @param i 下标
     * @param j 下标
     * @return
     */
    private static int dp(String str1, String str2, int i, int j) {
        if(i == -1){
            return j + 1;
        }
        if(j == -1){
            return i + 1;
        }
        if(str1.charAt(i) == str2.charAt(j)){
            return dp(str1,str2,i - 1, j -1);
        }else {
            return Math.min(Math.min(
                    dp(str1,str2,i - 1, j) + 1,
                    dp(str1, str2, i, j - 1) + 1
            ),
                    dp(str1,str2,i - 1, j - 1) + 1);
        }

    }

    /**
     * 思路：
     *  i ⾛完 s1 或 j ⾛完 s2 ，可以直接返回另⼀个字符串剩下的⻓度
     *  有四种可能
     *      啥也不干               待比较的两个字符相等，指针直接前移
     *      dp[i - 1][j] + 1,      待比较的两个字符不等，进行删除操作，操作数加一
     *      dp[i][j - 1] + 1       待比较的两个字符不等，进行插入操作，操作数加一
     *      dp[i - 1][j - 1] + 1)  待比较的两个字符数不等，进行替换操作，操作数加一
     * 后三种可能都有机会把字符串改成一致，但是需要看子问题的步数，所以取三者的最小值
     *
     * 发现dp[i][j] 只和它附近的三个状态有关
     * 所以还可以修改空间复杂度
     */

    private static int minDistanceStrConvertByOpt(String str1, String str2){
        int m = str1.length(), n = str2.length();
        // 存储s1[0..i] 和 s2[0..j] 的最⼩编辑距离(子结构)
        // dp可以类似为解决递归问题重复计算的重复子结构的备忘录
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                // 所以此时应该是i,j指针指向下一个元素
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(
                            dp[i][j - 1] + 1,
                            dp[i - 1][j] + 1
                            ),
                            dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }


    private static int minDistanceStrConvertByOptChoice(String str1, String str2) {

        int m = str1.length(), n = str2.length();
        // 存储s1[0..i] 和 s2[0..j] 的最⼩编辑距离(子结构)
        // dp可以类似为解决递归问题重复计算的重复子结构的备忘录
        DP[][] dp = new DP[m + 1][n + 1];

        // 设置边界条件，当有一方为长度为0时，所有的操作数就是另外一个子串的长度
        for(int i = 0; i <= m; i++){
            dp[i][0].setVal(i);
        }
        for(int j = 0; j <= n; j++){
            dp[0][j].setVal(j);
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= m; j++){
                // 所以此时应该是i,j指针指向下一个元素
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j].setVal(dp[i - 1][j - 1].getVal());
                    dp[i][j].setVal(0);
                }else {
                    if(dp[i][j - 1].getVal() < dp[i - 1][j].getVal()){
                        if(dp[i][j - 1].getVal() < dp[i - 1][j - 1].getVal()){
                            dp[i][j].setVal(dp[i][j-1].getVal() + 1);
                            dp[i][j].setChoice(2);
                        }else {
                            dp[i][j].setVal(dp[i - 1][j - 1].getVal() + 1);
                            dp[i][j].setChoice(3);
                        }
                    }else {
                        if(dp[i - 1][j].getVal() < dp[i - 1][j - 1].getVal()){
                            dp[i][j].setVal(dp[i - 1][j].getVal() + 1);
                            dp[i][j].setChoice(1);
                        }else {
                            dp[i][j].setVal(dp[i - 1][j - 1].getVal() + 1);
                            dp[i][j].setChoice(3);
                        }
                    }

                }
            }
        }

        return dp[m][n].getVal();

    }

    private static class DP{
        // 记录dp的值
        private int val;
        // 记录dp的选择
            // 0 代表啥都不做
            // 1 代表插⼊
            // 2 代表删除
            // 3 代表替换
        private int choice;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getChoice() {
            return choice;
        }

        public void setChoice(int choice) {
            this.choice = choice;
        }
    }

}
