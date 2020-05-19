package com.leetcode.learn.string;

import java.util.Stack;

/**
 * 括号匹配问题
 * 栈实现(用于对称结构)
 */
public class BracketsIsValid {
    public static void main(String[] args) {
        String str = "{}(((";
        boolean flag = bracketsIsValid(str);
        System.out.println(flag);
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
}
