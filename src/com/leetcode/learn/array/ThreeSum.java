package com.leetcode.learn.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {1,0,2,-1,0,0};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }

    //排序后动态规划
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // 排序
        numsList.sort(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o1 >= (Integer) o2 ? 1: -1;
            }
        });
        for(int i = 0; i < numsList.size() - 2; i++){
            if((i > 0 && !numsList.get(i).equals(numsList.get(i - 1))) || i == 0){
                int left = i + 1, right = numsList.size() - 1, target = -numsList.get(i);
                while(left < right){
                    if(numsList.get(left) + numsList.get(right) == target){
                        result.add(Arrays.asList(numsList.get(i), numsList.get(left), numsList.get(right)));
                        // 去重
                        while(left < right && numsList.get(left).equals(numsList.get(left + 1))){
                            left ++;
                        }
                        while(left < right && numsList.get(right).equals(numsList.get(right - 1))){
                            right --;
                        }
                        left ++;
                        right --;
                    }else if(numsList.get(left) + numsList.get(right) < target){
                        while(left < right && numsList.get(left).equals(numsList.get(left + 1))){
                            left ++;
                        }
                        left ++;
                    }else {
                        while(left < right && numsList.get(right).equals(numsList.get(right - 1))){
                            right --;
                        }
                        right --;
                    }

                }
            }
        }
        return result;
    }
}
