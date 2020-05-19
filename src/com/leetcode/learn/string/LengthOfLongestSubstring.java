package com.leetcode.learn.string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcdese";
        int len = newLengthOfLongestSubstrings(s);
        System.out.println(len);
    }



    /**
     * 获取最长不相同子串的长度
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstrings(String s) {
        int len = 0;
        if(!"".equals(s) && s != null){
            if(s.length() == 1){
                return s.length();
            }
            char[] chars = s.toCharArray();
            List<Character> charList = new ArrayList<>();
            List<Integer> lenList = new ArrayList<>();
            for (char c : chars) {
                charList.add(c);
            }
            HashSet<Character> hashSet = new HashSet<>(charList);
            if(hashSet.size() == 1){
                return hashSet.size();
            }
            for(int i = 0; i < charList.size(); i++){
                for(int j = i + 1; j < charList.size(); j++){
                    hashSet = new HashSet<>(charList.subList(i, j + 1));
                    if(hashSet.size() == j - i + 1){
                        lenList.add(hashSet.size());
                    }
                }
            }
            if(lenList.size() > 0){
                len = Collections.max(lenList);
            }
        }
        return len;
    }

    private static int newLengthOfLongestSubstrings(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        int leftIndex = 0;
        for (int j = 0; j < chars.length; j++) {
            for (int innerIndex = leftIndex; innerIndex < j; innerIndex++) {
                if (chars[innerIndex] == chars[j]) {
                    maxLength = Math.max(maxLength, j - leftIndex);
                    leftIndex = innerIndex + 1;
                    break;
                }
            }
        }
        return Math.max(chars.length - leftIndex, maxLength);
    }
}
