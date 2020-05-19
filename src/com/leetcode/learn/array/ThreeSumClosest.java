package com.leetcode.learn.array;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 寻找离target最近的三个数的和
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int target = 1;
        int[] nums = {0,2,1,-3};
        int closest = newThreeSumClosest(nums, target);
        System.out.println(closest);
    }


    private static int threeSumClosest(int[] nums, int target) {
        // 排序
        int result = -1;
        Arrays.sort(nums);
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // 找离平均值最近的
        int mean = target / 3;
        int meanDiff = Integer.MAX_VALUE;
        int r1Index = -1, r2Index = -1;
        int l1Index = -1, l2Index = -1;
        int l1Result = Integer.MAX_VALUE, l2Result = Integer.MAX_VALUE;
        int r1Result = Integer.MAX_VALUE, r2Result = Integer.MAX_VALUE;
        for(int i = 0; i < numsList.size(); i++){
            if(numsList.get(i) - mean < meanDiff && numsList.get(i) - mean >= 0){
                meanDiff = Math.abs(numsList.get(i) - mean);
                r1Index = i;
            }
        }
        meanDiff = Integer.MAX_VALUE;
        for(int i = 0; i < numsList.size(); i++){
            if(numsList.get(i) - (mean + 1) < meanDiff && numsList.get(i) - (mean + 1) >= 0){
                meanDiff = Math.abs(numsList.get(i) - mean);
                r2Index = i;
            }
        }
        meanDiff = Integer.MAX_VALUE;
        for(int j = 0; j < numsList.size(); j++){
            if(mean - numsList.get(j)< meanDiff && numsList.get(j) - mean < 0){
                meanDiff = Math.abs(numsList.get(j) - mean);
                l1Index = j;
            }
        }
        meanDiff = Integer.MAX_VALUE;
        for(int j = 0; j < numsList.size(); j++){
            if((mean + 1) - numsList.get(j)< meanDiff && numsList.get(j) - (mean + 1) < 0){
                meanDiff = Math.abs(numsList.get(j) - mean);
                l2Index = j;
            }
        }
        // 计算Result
        if(l1Index != -1){
            l1Result = getResult(numsList, l1Index, target);
        }
        if(l2Index != -1){
            l2Result = getResult(numsList, l2Index, target);
        }
        int lResult = Math.abs(l1Result - target) >= Math.abs(l2Result - target)? l2Result : l1Result;
        if(r1Index != -1){
            r1Result = getResult(numsList, r1Index, target);
        }
        if(r2Index != -1){
            r2Result = getResult(numsList, r2Index, target);
        }
        int rResult = Math.abs(r1Result - target) >= Math.abs(r2Result - target)? r2Result : r1Result;
        return Math.abs(lResult - target) >= Math.abs(rResult - target)? rResult : lResult;
    }

    private static int getResult(List<Integer> numsList, int index, int target) {
        int result = 0;
        if(index == 0){
            result = numsList.subList(index, index + 3).stream().mapToInt(e -> e).sum();
        }else if(index == numsList.size()){
            result = numsList.subList(index - 3, index).stream().mapToInt(e -> e).sum();
        }else if(index <= numsList.size() - 2){
            int center = numsList.subList(index - 1, index + 2).stream().mapToInt(e -> e).sum();
            if(index + 3 <= numsList.size()){
                int right = numsList.subList(index, index + 3).stream().mapToInt(e -> e).sum();
                result = Math.abs(center - target) >= Math.abs(right - target)? right : center;
            }else {
                result = center;
            }

        }
        return result;
    }

    private static int newThreeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int backMix = 0;
        Arrays.parallelSort(nums);
        for(int i=1;i<nums.length;i++){
            int k=i-1,j=i+1;
            while(k>=0 && j< nums.length){
                if((nums[i]+nums[k]+nums[j]-target) == 0){
                    return (nums[i]+nums[k]+nums[j]);
                }
                if(Math.abs((nums[i]+nums[k]+nums[j])-target) < min){
                    backMix = nums[i]+nums[k]+nums[j];
                    min = Math.abs((nums[i]+nums[k]+nums[j])-target);
                }
                if((nums[i]+nums[k]+nums[j]-target) > 0){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return backMix;
    }
}
