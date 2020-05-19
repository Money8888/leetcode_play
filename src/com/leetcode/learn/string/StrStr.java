package com.leetcode.learn.string;

/**
 * 字符串匹配算法
 */
public class StrStr {
    public static void main(String[] args) {
        String haystack = "", needle = "";
        int index = strStr(haystack, needle);
        System.out.println(index);
    }

    /**
     * 根据needle返回haystack的下标，如果没有则返回-1
     * @param haystack
     * @param needle
     * @return
     */
    private static int strStr(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        char[] t = haystack.toCharArray();
        char[] p = needle.toCharArray();
        int i = 0, j = 0;
        int[] next = getNext(needle);
        while (i < t.length && j < p.length){
            if(j == -1 || t[i] == p[j]){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }

        if(j == p.length){
            return i - j;
        }else {
            return -1;
        }
    }

    // 构建next数组
    private static int[] getNext(String needle) {
        char[] p = needle.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while(j < p.length - 1){
            if(k == -1 || p[j] == p[k]){
                if(p[++j] == p[++k]){
                    next[j] = next[k];
                }else {
                    next[j] = k;
                }
            }else {
                k = next[k];
            }
        }
        return next;
    }
}
