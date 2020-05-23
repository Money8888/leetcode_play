package com.leetcode.learn.opt;

import java.util.Arrays;

/**
 * 最长上升子序列
 * 返回子序列的长度
 * 比如
 * 10,9,2,5,3,7,101,18的最长上升子序列之一是2,5,7,101，长度为4
 * 采用动态规划
 */
public class MaxIncreSubList {
    public static void main(String[] args) {
        int[] array = new int[]{10,9,2,5,3,7,101,18};
        int len = maxIncreSubList(array);
        int binary = maxIncreSubListByBinary(array);
        System.out.println(binary);
        System.out.println(len);
    }

    /**
     * 采用动态规划
     * 时间复杂度O(N^2)
     * 空间复杂度O(N)
     * @param array
     * @return
     */
    private static int maxIncreSubList(int[] array) {
        // 设dp[i]为第i个元素(包含)以前的构成的子序列的最长上升子序列最大长度（重要）
        // dp[0] = 1
        int[] dp = new int[array.length + 1];
        // 初始化dp，最坏情况下是倒序时，每个元素自身是上升子序列，所以dp[i]=1
        Arrays.fill(dp, 1);
        // 构造状态转移等式 --数学归纳法
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < i; j++){
                // 如果array[i] < array[j] 对于所有的j都成立，则此时dp[i]一定为1
                // 当array[i] > array[j] 此时即为在j的子序列加一个下标为i的，所以序列长度为dp[j] +1
                // 因为j不止一个满足if条件，所以需取最长长度的dp[i]
                if(array[i] > array[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 结果
        int res = 0;
        for(int i = 0; i < array.length; i++){
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    // 二分查找法（神仙）
    // 10,9,2,5,3,7,101,18 分成
    /**
     * 10 9 2
     * 5 3
     * 7
     * 101 18
     * 四堆
     * 一定满足2 < 3 < 7 < 18 此时可以为最大递增序列
     * 所以堆数可以是最大递增序列的长度
     * 时间复杂度O(NlogN)
     */
    private static int maxIncreSubListByBinary(int[] array){
        // 最外面的元素
        int[] top = new int[array.length];
        // 初始化堆数
        int piles = 0;
        for(int i = 0; i < array.length; i++){
            // 待处理的数字
            int num = array[i];

            // 左侧右侧边界
            int left = 0, right = piles;
            // 开启左侧边界的二分查找
            // 对top数组的左侧边界的二分查找
            while (left < right){
                int mid = (left + right) / 2;
                // top为倒序顺序
                if(top[mid] >= num){
                    right = mid;
                }else if(top[mid] < num){
                    left = mid + 1;
                }
            }
            // 没有合适的堆，新建一堆
            if(left == piles){
                piles ++;
            }
            // 待处理数字放在一堆的最右侧
            top[left] = num;
        }
        return piles;
    }

}
