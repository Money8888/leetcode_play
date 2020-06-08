package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 直接选择排序
 * 当数组有序时也需要进行比较找最小值，
 * 时间复杂度O(n2),空间复杂度o(1),不稳定排序
 * */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3};
        selectSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 直接选择排序
    private static void selectSort(int[] array, int length) {

        int i, j, k;
        int temp;

        for(i = 0; i < length - 1; i++){
            k = i;
            // j一直在i的右侧进行扫描
            for(j = i + 1; j < length; j++){
                // 寻找最小值，如果没有小于array[k]的，k一直等于i
                if(array[j] < array[k]){
                    k = j;
                }
            }
            // 交换第i个和第k个值,若k=i也满足
            temp = array[i];
            array[i] = array[k];
            array[k] = temp;
        }

    }

}
