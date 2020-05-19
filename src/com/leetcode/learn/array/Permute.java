package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输出全排列
 * 1、递归
 * 2、回溯算法
 */
public class Permute {
    private static List<List<Integer>> result = new LinkedList<>();
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        //List<List<Integer>> permute = permute(nums);
        permute2(nums);
        //System.out.println(permute);
        System.out.println(result);
    }



    public static List<List<Integer>> permute(int[] nums) {
        return permuteMain(nums, nums.length);
    }

    // 采用递归？
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

    // 回溯
    private static List<List<Integer>> permute2(int[] nums) {
        // 记录轨迹
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(nums, trace);
        return result;
    }

    // 回溯函数
    private static void backtrace(int[] nums, LinkedList<Integer> trace) {
        // trace为全排列路径
        if(nums.length == trace.size()){
            result.add(new LinkedList<>(trace));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(trace.contains(nums[i])){
                continue;
            }
            trace.add(nums[i]);
            backtrace(nums, trace);
            trace.removeLast();
        }
    }
}
