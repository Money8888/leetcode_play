package com.leetcode.learn.dynamic_program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 正则表达式匹配
 */
public class StrRegex {

    public static void main(String[] args) {
        String text = "aa";
        String pattern = ".*";
        //boolean flag = isMathchStr(text, pattern);
        boolean flagByMemo = isMathchStrMemo(text, pattern);
        System.out.println(flagByMemo);
        //System.out.println(flag);
    }

    /**
     * 是否匹配
     * 暴力递归
     */
    private static boolean isMathchStr(String text, String pattern) {
        if(pattern.isEmpty()){
            return text.isEmpty();
        }

        boolean first = !text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.');
        if(pattern.length() >= 2 && pattern.charAt(1) == '#'){
            return (first && isMathchStr(text.substring(1), pattern)) || isMathchStr(text, pattern.substring(2));
        }else{
            return first && isMathchStr(text.substring(1), pattern.substring(1));
        }

    }

    private static Map<List<Integer>, Boolean> memo = new HashMap<>();
    private static boolean isMathchStrMemo(String text, String pattern){
        return dp(text, pattern, 0, 0);

    }

    static boolean ans;
    private static boolean dp(String text, String pattern, int i, int j) {
        List<Integer> keyList = new ArrayList<Integer>(){{add(i); add(j);}};
        if(memo.containsKey(keyList)){
            return memo.get(keyList);
        }
        if(j == pattern.length()){
            return i == text.length();
        }
        boolean first = (i < text.length()) && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');
        if(j < pattern.length() - 2 && pattern.charAt(j + 1) == '#'){
            ans = dp(text, pattern, i, j + 2) || (first && dp(text, pattern, i + 1, j));
        }else {
            ans = first && dp(text, pattern, i + 1, j + 1);
        }
        memo.put(
                new ArrayList<Integer>(){{add(i); add(j);}},
                ans
        );
        return ans;
    }
}
