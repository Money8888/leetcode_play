package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 按照顺时针方向遍历二维数组矩阵
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix.length == 0){
            return result;
        }
        // 定义上下左右边界
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(true){
            for(int i = left; i <= right; i++){
                result.add(matrix[up][i]);
            }
            if(++up > down){
                break;
            }
            for(int i = up; i <= down; i++){
                result.add(matrix[i][right]);
            }
            if(--right < left){
                break;
            }
            for(int i = right; i >= left; i--){
                result.add(matrix[down][i]);
            }
            if(--down < up){
                break;
            }
            for(int i = down; i >= up; i--){
                result.add(matrix[i][left]);
            }
            if(++left > right){
                break;
            }
        }
        return result;
    }
}
