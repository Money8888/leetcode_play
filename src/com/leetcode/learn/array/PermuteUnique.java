package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 *返回不重复的全排列
 */
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = {1,1,3};
        List<List<Integer>> permute = permuteUnique(nums);
        System.out.println(permute);
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = permuteMain(nums, nums.length);
        result = new ArrayList<>(new HashSet(result));
        return result;
    }

    private static List<List<Integer>> permuteMain(int[] nums, int len) {
        if(len == 0){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> pre = permuteMain(nums, len - 1);
        if(pre.size() == 0){
            result.add(new ArrayList<Integer>(){{
                add(nums[len - 1]);
            }});
            return result;
        }
        for (List<Integer> list : pre) {
            for(int i = 0; i <= list.size(); i++){
                List<Integer> tempList = new ArrayList<>(list);
                tempList.add(i, nums[len - 1]);
                result.add(tempList);
            }
        }

        return result;
    }
}
