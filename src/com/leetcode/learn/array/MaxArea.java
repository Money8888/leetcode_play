package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 横坐标代表长度，纵坐标代表高度，使面积最大
 */
public class MaxArea {
    public static void main(String[] args) {
        int[] y = {1,8,6,2,5,4,8,3,7};
        //int max = maxArea(y);
        int max = newMaxArea(y);
        System.out.println(max);
    }



    private static int maxArea(int[] y) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < y.length - 1; i++){
            for(int j = i + 1; j < y.length; j++){
                if((j - i) * Math.min(y[i], y[j]) >= max){
                    max = (j - i) * Math.min(y[i], y[j]);
                }
            }
        }

        return max;
    }

    // 贪心算法，选择指针所指的高较小的那个指针进行移动，以获得有更高的边的机会
    private static int newMaxArea(int[] y) {
        int len = y.length;
        // 声明两个指针
        int left = 0, right = len - 1;
        int ans = 0;
        while(left < right && left >= 0 && right < len){
            ans = Math.max(ans, (right - left) * Math.min(y[left], y[right]));
            if(y[left] > y[right]){
                -- right;
            }else {
                ++ left;
            }
        }
        return ans;
    }
}
