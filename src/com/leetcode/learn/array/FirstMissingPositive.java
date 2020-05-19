package com.leetcode.learn.array;

/**
 * 无序整数数组的缺失最小正整数
 * 常数级别的空间复杂度和o(n)级别的时间复杂度
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        int positive = firstMissingPositive(nums);
        int missingPositive = newFirstMissingPositive(nums);
        System.out.println(positive);
        System.out.println(missingPositive);
    }
    public static int firstMissingPositive(int[] nums) {
        // 不能考虑排序，不能考虑额外hash表
        // 原地数组哈希，值作为索引
        // 要找的数的肯定位于[1, nums.length+1]的闭区间里面
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 循环的意思是确保换过来的数和换过去的数保证在对应的序号上
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // 第二种方法
    public static int newFirstMissingPositive(int[] nums) {
        int res = 0;
        int n = nums.length - 1;
        int tmp = 0;

        while(res <= n){
            if(nums[res] == res + 1){
                res++;
            }else{
                tmp = nums[res];
                if(tmp > n + 1 || tmp < res + 1 || nums[tmp - 1] == tmp){
                    nums[res] = nums[n--];
                }else{
                    nums[res] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                }
            }
        }

        return res + 1;
    }

}
