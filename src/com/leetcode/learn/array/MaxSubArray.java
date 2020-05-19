package com.leetcode.learn.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 最大子数组和
 * 采用精妙的分治法求解
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int array = maxSubArray(nums);
        System.out.println(array);
    }

    /**
     * 分治算法
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int result = getMax(collect);
        return result;
    }

    private static int getMax(List<Integer> nums) {
        int n = nums.size();
        int max_left;
        int max_right;
        if (n == 1) {
            return nums.get(0);
        } else {
            // 递归计算左半边最大子序列和
            max_left = getMax(nums.subList(0, n / 2));
            max_right = getMax(nums.subList(n / 2, n));
        }

        int max_l = nums.get(n / 2 - 1);
        int tmp_l = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            tmp_l += nums.get(i);
            max_l = Math.max(tmp_l, max_l);
        }

        int max_r = nums.get(n / 2);
        int tmp_r = 0;
        for (int i = n / 2; i < n; i++) {
            tmp_r += nums.get(i);
            max_r = Math.max(tmp_r, max_r);
        }
        return Math.max(Math.max(max_left, max_right), max_r + max_l);
    }

    // 动态规划
    public static int newMaxSubArray(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for(int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0){
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
