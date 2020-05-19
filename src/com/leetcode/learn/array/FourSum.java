package com.leetcode.learn.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 四数之和等于target
 * 找出不相重复的四元组
 */
public class FourSum {
    public static void main(String[] args) {
        //int[] nums = {-500,-481,-480,-469,-437,-423,-408,-403,-397,-381,-379,-377,-353,-347,-337,-327,-313,-307,-299,-278,-265,-258,-235,-227,-225,-193,-192,-177,-176,-173,-170,-164,-162,-157,-147,-118,-115,-83,-64,-46,-36,-35,-11,0,0,33,40,51,54,74,93,101,104,105,112,112,116,129,133,146,152,157,158,166,177,183,186,220,263,273,320,328,332,356,357,363,372,397,399,420,422,429,433,451,464,484,485,498,499};
        int[] nums = {1,4,-3,0,0,0,5,0};
        int target = 0;
        //int target = 2139;
        //List<List<Integer>> result = newFourSum(nums, target);
        List<List<Integer>> result = fourSum(nums, target);
        System.out.println(result);
    }


    private static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Map<List<Integer>, Integer> map1 = new HashMap<>();
        Map<List<Integer>, Integer> map2 = new HashMap<>();
        for(int i = 0; i < nums.length / 2 - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                int finalI = i;
                int finalJ = j;
                map1.put(new ArrayList<Integer>(){{
                    add(finalI);
                    add(finalJ);
                }}, nums[i] + nums[j]);
            }
        }
        for(int i = nums.length / 2 ; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int finalI = i;
                int finalJ = j;
                map2.put(new ArrayList<Integer>(){{
                    add(finalI);
                    add(finalJ);
                }}, nums[i] + nums[j]);
            }
        }

        for (List<Integer> key1 : map1.keySet()) {
            for (List<Integer> key2 : map2.keySet()) {
                if(Collections.disjoint(key1, key2)){
                    if(map1.get(key1) + map2.get(key2) == target){
                        List<Integer> mergeList = new ArrayList<>();
                        mergeList.addAll(key1);
                        mergeList.addAll(key2);
                        List<Integer> collect = mergeList.stream().map(e -> nums[e]).collect(Collectors.toList());
                        result.add(collect);
                    }
                }
            }
        }
        for (List<Integer> list : result) {
            Collections.sort(list);
        }
        result = new ArrayList<>(new HashSet(result));
        return result;
    }

    private static List<List<Integer>> newFourSum(int[] nums, int target) {
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null||nums.length<4){
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length=nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for(int k=0;k<length-3;k++){
            /*当k的值与前面的值相等时忽略*/
            if(k>0&&nums[k]==nums[k-1]){
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if(min1>target){
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1<target){
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for(int i=k+1;i<length-2;i++){
                /*当i的值与前面的值相等时忽略*/
                if(i>k+1&&nums[i]==nums[i-1]){
                    continue;
                }
                /*定义指针j指向i+1*/
                int j=i+1;
                /*定义指针h指向数组末尾*/
                int h=length-1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                if(min>target){
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                if(max<target){
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j<h){
                    int curr=nums[k]+nums[i]+nums[j]+nums[h];
                    if(curr==target){
                        result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while(j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while(j<h&&i<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if(curr>target){
                        h--;
                    }else {
                        j++;
                    }
                }
            }
        }
        return result;
    }
}
