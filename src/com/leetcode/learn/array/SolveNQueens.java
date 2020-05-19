package com.leetcode.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后问题
 * 递归回溯算法
 */
public class SolveNQueens {

    // 记录某列是否已有皇后摆放
    static boolean[] col = null;

    // 记录某条正对角线（左上右下）是否已有皇后摆放（某条对角线对应的摆放位置为 r - i + n - 1） r表示行号，i表示列号
    static boolean[] left = null;

    // 记录某条斜对角线（左下右上）是否已有皇后摆放（某条对角线对应的摆放位置为 r + i）
    static boolean[] right = null;

    static List<List<String>> result = new ArrayList<>();

    public static void main(String[] args) {
        int n = 9;
        List<List<String>> queens = solveNQueens(n);
        System.out.println(queens);
    }

    public static List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        left = new boolean[2 * n - 1];
        right = new boolean[2 * n - 1];
        char[][] board = new char[n][n];
        // 调用递归回溯算法
        backTrack(board, 0, n);
        return result;
    }

    // 递归回溯
    private static void backTrack(char[][] board, int r, int n) {
        if(r >= n){
            List<String> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                list.add(new String(board[i]));
            }
            result.add(list);
            return;
        }
        // 初始化棋盘
        Arrays.fill(board[r], '.');
        for(int i = 0; i < n; i++){
            if(!col[i] && !left[r + i] && !right[r -i + n - 1]){
                //放置皇后
                board[r][i] = 'Q';
                col[i] = true;
                left[r + i] = true;
                right[r - i + n - 1] = true;
                backTrack(board, r + 1, n);
                // 回溯还原状态
                board[r][i] = '.';
                col[i] = false;
                left[r + i] = false;
                right[r - i + n - 1] = false;
            }
        }
    }
}
