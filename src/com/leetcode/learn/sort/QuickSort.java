package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 平均时间复杂度为O(nlogn)
 * 不稳定
 * 数据有序时性能最差为O(n2)，因为分成的两个区域第一个区域为空
 * 空间复杂度为O(nlogn)
 * 数据越随机分布效率越高
 * 每次不产生有序区，但归位一个元素 array[i],元素array[i]为枢轴元素
 * 最大递归深度为n,最小递归深度为logn
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3};
        qucikSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    // 快速排序
    private static void qucikSort(int[] array, int low, int high) {

        int i = low, j = high;
        int temp;
        if(i < j){
            temp = array[i];
            while (i != j){
                // j从右往左扫描，i从左往右扫描
                // 从右往左找到第一个小于temp的值的下标
                while (j > i && array[j] > temp){
                    j --;
                }
                if(i < j){
                    // 将此时小于temp的j的值赋给当前i的值
                    array[i] = array[j];
                    i ++;
                }
                // 此时从左往右找第一个大于temp的值
                while(i < j && array[i] < temp){
                    i++;
                }
                if(i < j){
                    array[j] = array[i];
                    j --;
                }
            }
            array[i] = temp;
            qucikSort(array, low , i - 1);
            qucikSort(array, i + 1, high);
        }

    }

}
