package com.leetcode.learn.array;

import javax.sound.sampled.EnumControl;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 求和使值等于target
 * 数组中的值可被重复使用
 * target = f(n) = f(n - 1) + nums[i]
 * f(n - 1) = target - nums[i]
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> lists = combinationSum(candidates, target);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        find(result, list, candidates, target, 0);
        return result;
    }

    private static void find(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int num) {
        if(target == 0){
            result.add(temp);
            return;
        }
        if(target < candidates[num]){
            return;
        }
        for(int i = num; i < candidates.length && candidates[i] <= target; i++){
            List<Integer> list = new ArrayList<>(temp);
            list.add(candidates[i]);
            find(result, list, candidates, target - candidates[i], i);
        }
    }
}
