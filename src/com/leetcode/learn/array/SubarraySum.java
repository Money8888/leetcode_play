package com.leetcode.learn.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断数组中有多少个(连续)子数组中元素数值相加后等于k
 * 前n项和
 */
public class SubarraySum {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,5};
        int count = subarraySum(nums,8);
        System.out.println(count);
        // System.out.println(subarraySumByHash(nums, 5));
    }

    // 常规方法 O(N^2)
    private static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀数组(即为前n项和),下标为n，值为S(n)
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for(int i = 0; i < n; i++){
            sum[i + 1] = sum[i] + nums[i];
        }

        int ans = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                // 计算num[j, i-1]的所有元素的和
                if(sum[i] - sum[j] == k){
                    ans++;
                }
            }
        }
        return ans;
    }

    // 改用hash
    private static int subarraySumByHash(int[] nums, int k) {
        int n = nums.length;
        // map的键为求的和的值，值为出现了多少次
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        int ans = 0;
        int sum_i = 0;
        for (int i = 0; i < n; i++){
            sum_i += nums[i];

            int sum_j = sum_i - k;
            if(preSum.containsKey(sum_j)){
                ans += preSum.get(sum_j);
            }

            preSum.put(sum_i, preSum.getOrDefault(sum_i,0) + 1);
        }

        return ans;
    }


}
