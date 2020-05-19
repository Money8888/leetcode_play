package com.leetcode.learn.num;

/**
 * 翻转数字，32位内存溢出则报0
 */
public class Reverse {
    public static void main(String[] args) {
        int x = 100012;
        int reverse = reverse(x);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }
}
