package com.leetcode.learn.dynamic_program.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 区间相交问题
 * 给定intvs = {{1,3}, {2,4}, {3,6}}
 * 最多有两个区间不相交
 * {1,3},{3,6}
 */
public class IntervalSchedule {

    public static void main(String[] args) {
        int[][] intvs = {{1,3}, {2,4}, {3,6}};
        int count = intervalSchedule(intvs);
        System.out.println(count);
    }

    /**
     * 采用贪心策略
     *    1、找到所有子区间中end最小的子区间
     *    2、然后排除与该子区间相重合的子区间
     *    3、重复1,2直到intvs为空为止
     */
    private static int intervalSchedule(int[][] intvs) {
        if(intvs.length == 0){
            return 0;
        }

        // 按照end值进行排序,升序
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        // 至少有一个区间不重合
        int count = 1;

        // 因为排完序，所以第一个元素的end一定是最小的
        int min_end = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if(start >= min_end){
                // 如果start小于min_end说明这个区间一定不与最小end区间重合
                // 一个区间的start和另一个区间的end边界值相等也意味着不相交
                count++;
                // 更新此时的全局最小，相当于把之前不重合的剔除
                min_end = interval[1];
            }
        }
        return count;

    }
}
