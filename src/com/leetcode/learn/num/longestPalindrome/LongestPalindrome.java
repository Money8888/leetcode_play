package com.leetcode.learn.num.longestPalindrome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 找到 s 中最长的回文子串
 * 即子串前后对称
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String str = "abaaba";
        String s = newLongestPalindrome(str);
        System.out.println(s);
    }



    /**
     * 求最长回文子串
     * 正着念反着念一样
     * @param str
     * @return
     */
    private static String  longestPalindrome(String str) {
        if("".equals(str) || str == null){
            return str;
        }
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
            // 截取字符串
            List<Integer> nextIndexList = getIndexList(str, str.charAt(i), i);
            System.out.println(nextIndexList);
        }

        stringList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() > o2.length()) {
                    return 1;
                }
                return 0;
            }
        });
        return stringList.get(0);

    }

    public static List<Integer> getIndexList(String s, char c, Integer ind) {
        String sub = s.substring(ind + 1);
        return IntStream.range(0, s.length())
                .filter(index -> sub.charAt(index) == c)
                .boxed()
                .collect(Collectors.toList());
    }

    private static String newLongestPalindrome(String s) {
        if("".equals(s) || s == null){
            return s;
        }
        // 记录回文串上下标
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }


    private static int findLongest(char[] str, int low, int[] range) {
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]){
            high ++;
        }
        int ans = high;
        while(low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]){
            low --;
            high ++;
        }

        if(high - low > range[1] - range[0]){
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

}
