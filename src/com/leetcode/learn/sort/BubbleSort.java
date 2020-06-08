package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 数据越接近正序性能越好，越接近反序性能越差
 * 每次归位一个元素
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3};
        bubbleSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 冒泡排序
    private static void bubbleSort(int[] array, int length) {

        int i, j;
        int temp;
        for(i = 1; i < length; i++){
            // 每次从0 ~ length -i 开始排，把最大值甩在最后
            for(j = 1; j <= length - i; j++){
                if(array[j ] < array[j - 1]){
                    // 相邻元素比较交换
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

    }
}
