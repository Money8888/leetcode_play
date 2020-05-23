package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并重叠元素数组
 * 贪心算法
 */
public class MergeCover {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] merge = merge(intervals);
        System.out.println(Arrays.deepToString(merge));
    }

    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if(len < 2){
            return intervals;
        }

        // 按照左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for(int i = 1; i < len; i++){
            int[] curInterval = intervals[i];

            int[] peek = result.get(result.size() - 1);

            if(curInterval[0] > peek[1]){
                result.add(curInterval);
            }else {
                peek[1] = Math.max(curInterval[1], peek[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
