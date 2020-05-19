package com.leetcode.learn.array;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = getTwoSumAnswer(nums, target);
        System.out.println(result.toString());
    }

    private static int[] getTwoSumAnswer(int[] nums, int target) {
        int[] reslut = new int[2];
        for(int i = 0; i< nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    reslut[0] = i;
                    reslut[1] = j;
                    return reslut;
                }
            }
        }
        return null;
    }
}
