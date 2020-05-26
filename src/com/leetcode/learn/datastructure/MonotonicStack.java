package com.leetcode.learn.datastructure;

import java.util.Arrays;
import java.util.Stack;

/**
 * 单调栈
 * 即有顺序的栈
 */
public class MonotonicStack {

    public static void main(String[] args) {
        int[] nums = {2,1,2,4,3};
        int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] nextGreater = nextGreaterElementCycle(nums);
        int[] result = dailyTemperatures(temp);
        System.out.println(Arrays.toString(nextGreater));
        System.out.println(Arrays.toString(result));
    }

    /**
     *返回下一个比本身元素大的数组，如果没有返回-1
     * 比如{2,1,2,4,3}
     * 返回{4,2,4,-1,-1},每一个元素向后看有没有比自己大的元素，没有就算-1，有就是就近
     * @return
     */
    private static int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];

        Stack<Integer> stack = new Stack<>();
        // 反着遍历，栈中存的是比数组中较大的元素，且栈底到栈顶依次递减
        for(int i = nums.length - 1; i >= 0; i--){
            while (!stack.empty() && stack.peek() <= nums[i]){
                stack.pop();
            }
            result[i] = stack.empty() ? -1: stack.peek();
            stack.push(nums[i]);
        }
        stack.clear();
        return result;
    }

    /**
     * @param nums 循环数组
     * @return
     */
    private static int[] nextGreaterElementCycle(int[] nums){
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        // 相当于假设将nums数组翻倍
        // 采用取余的方式可以有效的模拟出环形数组
        for(int i = 2 * nums.length - 1; i >= 0; i--){
            while (!stack.empty() && stack.peek() <= nums[i % nums.length]){
                stack.pop();
            }
            result[i % nums.length] = stack.empty() ? -1 : stack.peek();
            stack.push(nums[i % nums.length]);
        }
        return result;
    }

    /**
     * 返回右边比本身大的序号与自身的间距，没有则为0
     */
    private static int[] dailyTemperatures(int[] temp){
        int[] result = new int[temp.length];
        // 存放数组索引
        Stack<Integer> stack = new Stack<>();
        // 倒着遍历
        for(int i = temp.length - 1; i >= 0; i--){
            while (!stack.empty() && temp[stack.peek()] <= temp[i]){
                stack.pop();
            }
            result[i] = stack.empty()? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;
    }

}
