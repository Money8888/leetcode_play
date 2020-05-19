package com.leetcode.learn.num.longestPalindrome;

/**
 * 判断数是否是回文数
 */
public class IsPalindrome {
    public static void main(String[] args) {
        int num = 121;
        boolean flag = isPalindrome(num);
        System.out.println(flag);
    }

    private static boolean isPalindrome(int num) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(num);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(num);
        sb2.reverse();
        return String.valueOf(sb1).equals(String.valueOf(sb2));
    }
}
