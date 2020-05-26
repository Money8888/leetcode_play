package com.leetcode.learn.dynamic_program;

/**
 * 盗贼问题
 * 房屋为环形房屋
 * 不能抢相邻的
 */
public class Rob {
    public static void main(String[] args) {
        int[] room = {2,3,6,3,1,3};
        int max = rob(room);
        System.out.println(max);
    }

    // 动态规划
    // 空间复杂度O(1)
    private static int rob(int[] room) {
        int n = room.length;
        if(n == 1){
            return room[0];
        }
        // 处理环形状态，求取最大值
        return Math.max(robMain(room,1, n - 1), robMain(room, 0, n - 2));

    }

    private static int robMain(int[] room, int start, int end){
        int n = room.length;
        // dp_i_1为间隔1的房屋，dp_i_2为间隔2的房屋
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, room[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
