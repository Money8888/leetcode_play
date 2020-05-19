package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组跳跃，使得跳跃次数最少能从数组一端跳到另一端
 * 每次跳跃的幅度不大于在所在位置的元素大小
 * f(i+1) <= f(i) + nums[f(i)] i = 0,,,N
 * 求min N 且 f(N) = len - 1
 */
public class Jump {

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        int jump = jump(nums);
        System.out.println(jump);
    }

    public static int jump(int[] nums) {
        int len = nums.length;
        int i = 1;
        return 1;


    }

    private static int f(int i, int[] nums) {
        if(i == 0){
            return 0;
        }
        return f(i - 1, nums) + nums[f(i - 1, nums)];
    }
}
