package com.leetcode.learn.dynamic_program;


import java.util.HashMap;
import java.util.Map;

/**
 * 青蛙跳跃台阶
 * 每次只能跳一步或两步，跳跃n个台阶有多少种跳法
 */
public class FogJump {

    public static void main(String[] args) {
        int n = 8;
        int jump = fogJump3(n);
        System.out.println(jump);
    }

    /**
     * 暴力递归方法
     * 重算了很多，比如fogJump(2)被算了多次
     * @param n
     * @return
     */
    private static int fogJump1(int n){
        // 递归出口
        if(n < 2){
            return n;
        }

        return fogJump1(n - 1) + fogJump1(n -2);
    }

    // 空间换时间，存储一部分已经计算过的值
    // 采用hashmap
    // 键为函数自变量，值为函数值
    // 也可以用数组的下标来存储，下标表示自变量，值表示访问过的标记
    static Map<Integer, Integer> cache = new HashMap<>();

    private static int fogJump2(int n){
        if(n < 2){
            return n;
        }
        if(cache.containsKey(n)){
            // 直接从map中获取
            return cache.get(n);
        }else {
            int m = fogJump2(n - 1) + fogJump2(n - 2);
            cache.put(n, m);
            return m;
        }
    }

    // 问题本身是一个斐波拉契数列
    // n=1,sum=1 n=2 sum=2 n=3,sum=3 n=4,sum=5
    // 方法2的hashmap需要占用空间复杂度
    private static int fogJump3(int n){
        if(n < 2){
            return n;
        }

        int f1 = 0;
        int f2 = 1;
        int sum  = 0;
        for(int i = 1; i < n; i++){
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }

        return sum;
    }

}
