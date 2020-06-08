package com.leetcode.learn.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 * a.将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
 * 复杂度O(nlogn),空间复杂度O(1)
 * 不稳定
 * 与原始数据顺序物管
 * 每一趟归位一个元素，产生一个有序区
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,6,9,0,3};
        heapSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    // 堆排序
    private static void heapSort(int[] array, int length) {

        int i;
        int temp;
        // 从n/2开始往根节点靠拢
        for(i = length / 2 - 1; i >= 0; i--){
            // 从第一个非叶子结点从下至上，从右至左调整结构
            // 建立初始堆
            shift(array, i, length);
        }
        // 调整堆结构+交换堆顶元素与末尾元素
        for(i = length - 1; i > 0; i--){
            // 将大根与最后一个节点交换
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 将剩余n - i个元素重新建堆
            shift(array, 0, i);
        }

    }

    // 堆调整函数, i为当前父节点下标
    private static void shift(int[] array, int i, int length) {
        // 保存当前父节点的值
        int temp = array[i];
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){
            // i结点的左子结点下标2i+1处开始
            if(k + 1 < length && array[k] < array[k + 1]){
                //如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(array[k] > temp){
                // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                array[i] = array[k];
                i = k;
            }else{
                break;
            }
        }
        array[i] = temp;
    }

}
