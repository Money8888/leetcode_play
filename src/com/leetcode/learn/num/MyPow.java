package com.leetcode.learn.num;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 自定义指数函数
 */
public class MyPow {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = Integer.MIN_VALUE;
        double pow = newMyPow(x, n);
        System.out.println(pow);
    }

    public static  double newMyPow(double x, int n){
        if (n == 0) { return 1; }
        if (n == 1) { return x; }
        if (n == -1) { return 1 / x; }
        double half = myPow(x, n / 2);
        double rest = myPow(x, n % 2);
        return rest * half * half;
    }

    // 会报超时
    public static double myPow(double x, int n) {

        if(n == 0 && x != 0){
            return 1;
        }
        if(n <= 0 && x == 0){
            return Double.NaN;
        }
        double result = x;
        if(n < 0){
            while(Math.abs(++n) > 0){
                result *= x;
            }
            result = 1 / result;
        }else {
            while(Math.abs(--n) > 0){
                result *= x;
            }
        }

        return result;
    }
}
