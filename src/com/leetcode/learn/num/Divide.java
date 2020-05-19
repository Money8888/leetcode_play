package com.leetcode.learn.num;

import java.util.Map;

/**
 * 两数相除
 * 更相减损术
 */
public class Divide {

    public static void main(String[] args) {
        int dividend = 1000;
        int divisor = -Integer.MAX_VALUE;
        //int divide = divide(dividend, divisor);
        int divide = newDivide(dividend, divisor);
        System.out.println(divide);
    }



    public static int divide(int dividend, int divisor) {
        if(divisor == 0){
            throw new RuntimeException("除数不能为0");
        }
        boolean flag = true;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ){
            flag = false;
        }
        if(dividend == Integer.MIN_VALUE && divisor < 0){
            dividend = Integer.MAX_VALUE;
        }
        if(divisor == Integer.MIN_VALUE && dividend < 0){
            divisor = Integer.MAX_VALUE;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int diff = dividend - divisor, mod = 0;
        while(diff >= 0){
            mod++;
            diff = diff - divisor;
        }
        return flag? mod: -mod;
    }

    // 位运算(除2取余法)
    private static int newDivide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) <0;
        long t = Math.abs((long) dividend);
        long d= Math.abs((long) divisor);
        int result = 0;
        for(int i = 31; i >= 0; i--){
            // t << i 表示t * 2^i
            if((t>>i) >= d){
                result += 1 << i;
                t -= d << i;
            }
        }
        return negative ? -result : result;
    }
}
