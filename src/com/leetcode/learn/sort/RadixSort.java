package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3};
        radixSort(array, array.length, 10);
        System.out.println(Arrays.toString(array));
    }

    // 基数排序,k为位数 这里取100
    private static void radixSort(int[] array, int length, int d) {

        int i, j, k;
        k = 10;
        for(i = 1; i <= d; i = i * 10){
            // 列表示可能的数字这里为0~9,行表示位数相同的元素个数
            // java二维数组的默认值是0
            int[][] temp = new int[length][k];
            for(j = 0; j < length; j++){
                int p = (array[j] / i) % 10;
                temp[j][p] = array[j];
            }
            int q = 0;
            for(int a = 0; a < length; a++){
                for (int b = 0; b < d; b++){
                    if(temp[a][b] != 0){
                        array[q++] = temp[a][b];
                    }
                }
            }
        }

    }

}