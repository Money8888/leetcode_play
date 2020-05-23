package com.leetcode.learn.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高楼扔鸡蛋
 * 返回最坏情况下需要至少扔多少次鸡蛋才能确定鸡蛋恰好不碎的楼层数
 */
public class TallThrowEggs {

    public static void main(String[] args) {
        int tall = 10;
        int eggs = 5;
        int num = tallThrowEggs(tall, eggs);
        int numByOther = tallThrowEggsByOtherOpt(tall, eggs);
        System.out.println(numByOther);
        System.out.println(num);
    }


    private static int tallThrowEggs(int eggs, int tall) {
        // 构造dp，含义是数量为eggs的鸡蛋在高度为tall的高楼最坏情况下至少扔的次数
        int count = dp(eggs, tall);
        // 采用二分查找优化
        int countByBin = dpByBin(eggs, tall);
        return countByBin;
    }



    // 新建备忘录
    private static Map<List<Integer>, Integer> map = new HashMap<>();

    // 总时间复杂度是 O(K*N^2), 空间复杂度 O(KN).
    private static int dp(int eggs, int tall) {
        if(tall == 0){
            return 0;
        }
        if(eggs == 1){
            return tall;
        }

        List<Integer> keyList = new ArrayList<>();
        keyList.add(eggs);
        keyList.add(tall);
        if (map.containsKey(keyList)){
            return map.get(keyList);
        }
        int res = Integer.MAX_VALUE;
        for(int h = 1; h <= tall; h++){
            res = Math.min(res, Math.max(
                    // 鸡蛋没碎的情况
                    dp(eggs, tall - h),
                    // 鸡蛋碎了的情况
                    dp(eggs - 1, h - 1)
            ) + 1);
        }
        map.put(new ArrayList<Integer>(){{
            add(eggs);
            add(tall);
        }}, res);

        return res;
    }

    // 采用二分查找优化
    // 总时间复杂度是 O(K*N*logN), 空间复杂度 O(KN)
    private static int dpByBin(int eggs, int tall) {
        if(tall == 0){
            return 0;
        }
        if(eggs == 1){
            return tall;
        }

        List<Integer> keyList = new ArrayList<>();
        keyList.add(eggs);
        keyList.add(tall);
        if (map.containsKey(keyList)){
            return map.get(keyList);
        }
        int res = Integer.MAX_VALUE;

        int low = 1, high = tall;
        while (low <= high){
            int mid = (low + high) / 2;

            int broken = dpByBin(eggs - 1, mid - 1);
            int not_broken = dpByBin(eggs, tall - mid);
            if(broken > not_broken){
                high = mid - 1;
                res = Math.min(res, broken + 1);
            }else {
                low = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }
        map.put(new ArrayList<Integer>(){{
            add(eggs);
            add(tall);
        }}, res);
        return res;
    }


    // 神仙操作
    private static int tallThrowEggsByOtherOpt(int eggs, int tall){

        //dp[k][m] = n 表示当前有 k 个鸡蛋，可以尝试扔 m 次鸡蛋
        //这个状态下，最坏情况下最多能确切测试⼀栋 n 层的楼
        int[][] dp = new int[eggs + 1][tall + 1];

        int m = 0;
        while (dp[eggs][m] < tall){
            m++;
            for(int k = 1; k <= eggs; k++){
                // 总的楼层数 = 楼上的楼层数 + 楼下的楼层数  + 1（当前这层楼）
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }
}
