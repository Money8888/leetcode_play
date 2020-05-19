package com.leetcode.learn.array;

/**
 * 按值查找非重复数组
 * 如果不存在则返回该插入的位置下标
 */
public class SearchInsert {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6,10};
        int target = 11;
        int insert = searchInsert(nums, target);
        System.out.println(insert);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left != right) {
            int mid = left + (right - left) / 2;
            if(target < nums[mid]) {
                right = mid;
            }else if(target > nums[mid]){
                left = mid + 1;
            }else if(target == nums[mid]){
                return mid;
            }
        }
        return left;


    }
}
