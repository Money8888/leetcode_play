package com.leetcode.learn.array;

/**
 * 数组中元素查找
 * 数组半有序
 * 要求o(logn)复杂度
 * 1 2 3 4 5 6 7 可以大致分为两类，
 * 第一类 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。
 * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
 * 第二类 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。
 * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找
 */
public class Search {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int search = search(nums, target);
        System.out.println(search);
    }

    public static int search(int[] nums, int target) {
        int index = binSearch(nums, 0, nums.length - 1, target);
        return index;

    }

    public static int binSearch(int[] nums, int start, int end, int target){
        if(start > end){
            return -1;
        }
        int mid = (start + end) / 2;
        if(nums[mid] == target){
            return mid;
        }
        // 分情况讨论
        if(nums[start] <= nums[mid]){
            if(nums[start] <= target && target < nums[mid]){
                return binSearch(nums, start, mid -1, target);
            }else {
                return binSearch(nums, mid + 1, end, target);
            }
        }else {
            if(nums[mid] < target && target <= nums[end]){
                return binSearch(nums, mid + 1, end, target);
            }else {
                return binSearch(nums, start, mid - 1, target);
            }
        }
    }
}
