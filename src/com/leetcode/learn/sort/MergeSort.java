package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 递归和非递归版
 * 归并排序的效率与原始数据顺序无关
 * 平均时间复杂度 O(nlogn)
 * 空间复杂度O(n)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3,7};
        // mergeSort(array, 0, array.length - 1);

        // 非递归版
        mergeSortByCycle(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 递归版
    private static void mergeSort(int[] array, int low, int high) {
        int mid;
        if(low >= high){
            return;
        }
        mid = (high - low) / 2 + low;
        // 归并前半个子序列
        mergeSort(array, low, mid);
        // 归并后半个子序列
        mergeSort(array, mid + 1, high);
        // 合并两个子序列
        Merge(array, low, mid, high);
    }

    // 合并函数, 将array[low,mid],array[mid+1,high]合并成array[low,hign]
    private static void Merge(int[] array, int low, int mid, int high) {

        int[] temp = new int[array.length];
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high){
            if(array[i] <= array[j]){
                temp[k++] = array[i++];
            }else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid){
            temp[k++] = array[i++];
        }
        while (j <= high){
            temp[k++] = array[j++];
        }
        for(i = low; i <= high; i++){
            array[i] = temp[i];
        }

    }

    // 非递归版
    private static void mergeSortByCycle(int[] array, int length) {

        // 初始归并段长度
        int h = 1;
        while (h < length){
            MergePass(array, length, h);
            h = 2 * h;
        }

    }

    private static void MergePass(int[] array, int length, int h) {
        int i = 0;
        while (i <= length - 2 * h){
            // 合并array[i, i + h -1]和 array[i, i+h, i + 2h -1]
            Merge(array, i, i + h - 1, i + 2 * h - 1);
            i = i + 2 * h;
        }
        // 剩余的
        if(i < length - h){
            Merge(array, i, i + h - 1, length - 1);
        }
    }
}
