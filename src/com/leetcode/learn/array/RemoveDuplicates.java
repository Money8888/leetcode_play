package com.leetcode.learn.array;

import java.util.Arrays;

/**
 * 返回不重复的数组的大小和变化后的nums，空间复杂度为o(1)
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        //int len = removeDuplicates(nums);
        int len = newRemoveDuplicates(nums);
        System.out.println(len);
    }

    private static int newRemoveDuplicates(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        int j = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[j] != nums[i]){
                nums[++j] = nums[i];
            }
        }
        return ++j;
    }

    private static int removeDuplicates(int[] nums) {
        if(nums.length == 0 || nums == null){
            return 0;
        }
        int count = 1;
        int temp;
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(!judgeBelongto(nums[j], Arrays.copyOfRange(nums, 0, i + 1))){
                    temp = nums[i + 1];
                    nums[i + 1] = nums[j];
                    nums[j] = temp;
                    count++;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;

    }

    // 判断指定数是否在数组中
    private static boolean judgeBelongto(int num, int[] nums) {
        boolean flag = false;
        for (int value : nums) {
            if (value == num) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
