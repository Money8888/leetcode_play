package com.leetcode.learn.string;

import java.util.*;

/**
 * 根据指定的数据生成所有可能的括号匹配
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        int n = 3;
        //List<String> result1 = generateParenthesis1(n);
        //List<String> result2 = generateParenthesis2(n);
        List<String> result3 = generateParenthesis3(n);
        System.out.println(result3);
    }



    // 深度优先遍历
    private static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if(n == 0){
            return result;
        }
        // 执行深度优先遍历
        dfs("", n, n, result);
        return result;
    }

    /**
     * 深度优先遍历函数
     * @param curStr 当前递归得到对结果
     * @param left 左括号剩余几个可使用
     * @param right 右括号剩余几个可使用
     * @param result 结果集
     */
    private static void dfs(String curStr, int left, int right, List<String> result) {
        if(left == 0 && right == 0){
            result.add(curStr);
            return;
        }
        // 剪枝 左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝
        if(left > right){
            return;
        }
        if(left > 0){
            dfs(curStr + "(", left - 1, right, result);
        }
        if(right > 0){
            dfs(curStr + ")", left, right - 1, result);
        }
    }

    static class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    // 广度优先遍历
    private static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        // offer进队函数
        queue.offer(new Node("", n, n));
        while(!queue.isEmpty()){
            // poll出队函数
            Node curNode = queue.poll();
            if(curNode.left == 0 && curNode.right == 0){
                res.add(curNode.res);
            }
            if(curNode.left > 0){
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if(curNode.right > 0 && curNode.left < curNode.right){
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 自底向上，无后效性
     * @param n
     * @return
     */
    private static List<String> generateParenthesis3(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        // 定义状态
        List<List<String>> dp = new ArrayList<>();
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);
        for (int i = 1; i <= n; i++){
            List<String> cur = new ArrayList<>();
            for(int j = 0; j < i; j++){
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for(String s1: str1){
                    for(String s2 : str2){
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }
}
