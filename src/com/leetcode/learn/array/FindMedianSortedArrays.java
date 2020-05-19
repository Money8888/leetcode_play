package com.leetcode.learn.array;

import java.beans.IntrospectionException;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {20};
        double a = findMedianSortedArrays(nums1, nums2);
        System.out.println(a);
    }

    /**
     * 参考大佬的代码
     * @param nums1 有序数组
     * @param nums2
     * @return
     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int left = (l1 + l2 + 1) / 2;
        int right = (l1 + l2 + 2) / 2;
        return (findIndex(nums1, 0, nums2, 0, left) + findIndex(nums1, 0, nums2,0, right)) / 2;
    }

    /**
     * 根据下标找两个数组对应的元素
     * @param nums1
     * @param i nums1起始位置
     * @param nums2
     * @param j nums2起始位置
     * @param index
     * @return
     */
    private static double findIndex(int[] nums1, int i, int[] nums2, int j, int index) {
        if(i >= nums1.length){
            return nums2[j + index -1];
        }
        if(j >= nums2.length){
            return nums1[i + index - 1];
        }
        if(index == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        // 二分查找
        int midVal1 = (i + index / 2 -1 < nums1.length) ? nums1[i + index / 2] : Integer.MAX_VALUE;
        int midVal2 = (j + index / 2 -1 < nums2.length) ? nums2[j + index / 2] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findIndex(nums1, i + index / 2, nums2, j, index - index / 2);
        }else {
            return findIndex(nums1, i, nums2, j + index / 2, index - index / 2);
        }
    }
}
