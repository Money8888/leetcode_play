package com.leetcode.learn.string.letterCombinations;

import java.util.List;

public class MainRun {
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> result = letterCombinations.letterCombinations(digits);
        System.out.println(result);
    }
}
