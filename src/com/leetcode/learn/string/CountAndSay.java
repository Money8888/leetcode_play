package com.leetcode.learn.string;

/**
 * 根据读法生成序列字符串
 * 1<=n<=30
 */
public class CountAndSay {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(countAndSay(n));
    }

    public static String countAndSay(int n) {
        if(n == 1){
            return String.valueOf(n);
        }
        String pre = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        if(pre.length() == 1){
            sb.append(1);
            sb.append(pre);
            return String.valueOf(sb);
        }
        if(!"".equals(pre)){
            for(int i = 0; i <= pre.length() - 1; i++){
                if(i + 1 == pre.length()){
                    sb.append(1);
                    sb.append(pre.charAt(i));
                    break;
                }
                if(pre.charAt(i) != pre.charAt(i + 1)){
                    sb.append(1);
                    sb.append(pre.charAt(i));
                }else {
                    int num = 1;
                    while (i < pre.length() - 1 && pre.charAt(i) == pre.charAt(i + 1)){
                        num += 1;
                        i++;
                    }
                    sb.append(num);
                    sb.append(pre.charAt(i - 1));
                }
            }
        }
        return String.valueOf(sb);
    }
}
