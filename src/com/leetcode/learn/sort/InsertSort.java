package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 直接插入排序和希尔排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3,7};
        insertSort(array, array.length);
        // shellSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 插入排序
    private static void insertSort(int[] array, int length) {

        int temp;
        int j;
        //
        for(int i = 1; i < length; i++){
            temp = array[i];
            // 每次是[0, i-1]的元素与第i个元素进行的比较
            // [0,j]是有序的，array[j]是最大的值
            for(j = i - 1; j >= 0 && array[j] > temp; j --){
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }

    }

    private static void shellSort(int[] array, int length) {

        for(int gap = length / 2; gap > 0; gap = gap /2){
            // 从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i = gap; i < length; i++){
                int j = i;
                int temp = array[j];
                if(array[j] < array[j - gap]){
                    while (j - gap >= 0 && array[j - gap] > temp){
                        array[j] = array[j - gap];
                        j-= gap;
                    }
                    array[j] = temp;
                }
            }
        }

    }


}
