package com.leetcode.learn.string;

/**
 * 字符串的乘法
 * 不能事先将字符串转成数值进行乘法
 */
public class Multiply {

    public static void main(String[] args) {
        String num1 = "1232282947923749824792927349823742193290332";
        String num2 = "45621894729438729387492387432984740923734932482103981209382";
        System.out.println(multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        // 字符串长度小于110
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0){
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();
    }
}
