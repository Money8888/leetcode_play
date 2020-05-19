package com.leetcode.learn.string;

import java.util.Stack;

/**
 * 最长有效括号
 * 巧妙☆
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String str = ")()())";
        int i = newLongestValidParentheses(str);
        System.out.println(i);
    }

    public static int longestValidParentheses(String s) {
        String sb;
        int max = 0;
        for(int i = 0; i <= s.length() - 1; i++){
            for(int j = i + 1; j <= s.length(); j++){
                sb = s.substring(i, j);
                if(bracketsIsValid(sb)){
                    if(max < j - i){
                        max = j - i;
                    }
                }
            }
        }
        return max;
    }

    private static boolean bracketsIsValid(String str) {
        // new一个栈
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static int newLongestValidParentheses(String s){
        char[] chars = s.toCharArray();
        // 从左从右扫描字符串，取长度最大的字符串
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
        
    }

    private static int calc(char[] chars, int i, int flag, int end, char cTem) {
        int max = 0, sum = 0, currLen = 0, validLen = 0;
        for(; i != end; i += flag){
            sum += (chars[i] == cTem ? 1 : -1);
            currLen ++;
            if(sum < 0){
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
            }else if(sum == 0){
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;

    }

}
