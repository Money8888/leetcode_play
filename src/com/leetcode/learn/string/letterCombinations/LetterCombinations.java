package com.leetcode.learn.string.letterCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含电话数字 2-9 的字符串，返回所有它能表示的字母组合
 *
 * 回溯算法
 */
public class LetterCombinations {

    List<String> res = new ArrayList<String>();
    StringBuilder tmp = new StringBuilder();

    Map<String, String> digitsMap = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0){
            return res;
        }
        process(digits, 0);
        return res;
    }


    private void process(String digits, int index) {
        if (index >= digits.length()) {
            res.add(tmp.toString());
            return;
        }
        String letter = digitsMap.get(digits.substring(index, index + 1));
        for (int i = 0; i < letter.length(); i++) {
            tmp.append(letter, i, i + 1);
            process(digits, index + 1);
            //去掉添加的字母，开始回溯
            tmp.replace(tmp.length() -1, tmp.length(),"");
        }
    }
}
