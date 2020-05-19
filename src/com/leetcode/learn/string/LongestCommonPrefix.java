package com.leetcode.learn.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长公共前缀
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"dog","racecar","car"};
        String str = longestCommonPrefix(strs);
        System.out.println(str);
    }

    private static String longestCommonPrefix(String[] strs) {
        // 找到最小长度字符串
        int minLen = Integer.MAX_VALUE;
        String prefixStr = "";
        String minStr = "";
        for (String str : strs) {
            if(str.length() < minLen){
                minLen = str.length();
                minStr = str;
            }
        }
        int i;
        List<Boolean> flagList = new ArrayList<>();
        for(i = minStr.length(); i >= 0; i--){
            String tempStr = minStr.substring(0, i);
            for (String str : strs) {
                flagList.add(str.startsWith(tempStr));
            }
            if(!flagList.contains(false)){
                prefixStr = tempStr;
                break;
            }
            flagList.clear();
        }
        return prefixStr;
    }
}
